package lb.project.lb6_server.lib.senders;

import lb.project.lb6_server.server.logic.controllers.CommandsController;
import lb.project.lb6_server.lib.messages.Message;
import lb.project.lb6_server.server.logic.controllers.ICommandsController;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SynchronizedExchangeChannel implements IExchangeChannel{
    private Selector selector;

    private ICommandsController commandsController;
    private ThreadLocal<SocketAddress> target;
    private DatagramChannel channel;

    private ExecutorService pool = Executors.newCachedThreadPool();

    public SynchronizedExchangeChannel(SocketAddress target, SocketAddress host) {
        this(host);
        this.target = ThreadLocal.withInitial(() -> target);
    }

    public void setCommandsController(CommandsController commandsController) {
        this.commandsController = commandsController;
    }

    public SynchronizedExchangeChannel(SocketAddress host) {
        try {
            channel = DatagramChannel.open();
            channel.socket().setSoTimeout(5000);
            channel.configureBlocking(false);
            channel.bind(host);

            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendMesssage(Message message) {
        if (target == null || target.get() == null) return false;
        SocketAddress address = target.get();
        Future<Boolean> result = pool.submit(() -> {
            ByteBuffer buffer = ByteBuffer.wrap(SerializationUtils.serialize(message));
            try {
                int bytesSent = channel.send(buffer, address);
                return bytesSent > 0;
            } catch (Exception e) {
                return false;
            }
        });

        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendMesssage(Message message, SocketAddress address) {
        if (address == null) return false;
        Future<Boolean> result = pool.submit(() -> {
            ByteBuffer buffer = ByteBuffer.wrap(SerializationUtils.serialize(message));
            try {
                int bytesSent = channel.send(buffer, address);
                return bytesSent > 0;
            } catch (Exception e) {
                return false;
            }
        });

        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message recieveMessage() {
        return null;
    }



    public Message receiveMessage(SelectionKey key) {
        ByteBuffer buffer = ByteBuffer.allocate(4000);
        try {
            DatagramChannel dc = (DatagramChannel) key.channel();
            SocketAddress sender = dc.receive(buffer);
            if (sender != null) {
                target = ThreadLocal.withInitial(() -> sender);
                Message message = extractMessage(buffer);
                if(message != null)
                    message.setSender(sender);

                return message;
            }
        } catch (IOException e) {
            System.out.println("Адрес недоступен!");
            throw new RuntimeException(e);
        }
        return null;
    }

    public Message recieveMessageWithTimeOut() {
        ByteBuffer buffer = ByteBuffer.allocate(4000);
        try {
            if (selector.select(5000) == 0) {
                return null; // Timeout occurred
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    iterator.remove();
                    DatagramChannel dc = (DatagramChannel) key.channel();
                    SocketAddress sender = dc.receive(buffer);
                    if (sender != null) {
                        target = ThreadLocal.withInitial(() -> sender);
                    }
                    return extractMessage(buffer);
                }
            }
        } catch (SocketTimeoutException ex) {
            return null;
        } catch (IOException e) {
            System.out.println("Адрес недоступен!");
            throw new RuntimeException(e);
        }
        return null;
    }

    private Message extractMessage(ByteBuffer buffer) {
        Message msg;
        try {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            msg = (Message) SerializationUtils.deserialize(bytes);
        } catch (Exception e) {
            return null;
        }
        return msg;
    }

    public Selector getSelector() {
        return selector;
    }

    public void processRequests() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isReadable()) {
                        iterator.remove();

                        executorService.submit(() -> {
                            try {
                                Message message = receiveMessage(key);
                                if (message != null) {
                                    // Передаем команду на выполнение
                                    commandsController.getCommand(message.getCommandName()).exexute(message.getEntity(), message.getUser(), message.getSender());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке запросов: " + e.getMessage());
        }
    }

}
