package lb.project.lb6_server.lib.senders;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.util.SerializationUtils;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExchangeChannel implements IExchangeChannel {

    private SocketAddress target;
    private DatagramChannel channel;


    private ExecutorService pool = Executors.newCachedThreadPool();

    public ExchangeChannel(SocketAddress target, SocketAddress host) {
        this(host);
        this.target = target;
    }

    public ExchangeChannel(SocketAddress host) {
        try {
            channel = DatagramChannel.open();
            channel.socket().setSoTimeout(200000);
            channel.bind(host);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendMesssage(Message message) {

        if (target == null) return false;
        ByteBuffer buffer = ByteBuffer.wrap(SerializationUtils.serialize(message));
        try {
            channel.send(buffer, target);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Message recieveMessage() {
        ByteBuffer buffer = ByteBuffer.allocate(4000);
        try {
            target = channel.receive(buffer);
        } catch (IOException e) {
            System.out.println("Адрес недоступен!");
            throw new RuntimeException(e);
        }

        Message message = extractMessage(buffer);
        return message;
    }

    public Message recieveMessageWithTimeOut() {

        byte[] bytes = new byte[4000];
        DatagramPacket packet;
        try {
            packet = new DatagramPacket(bytes, 4000);
            channel.socket().receive(packet);
            target = packet.getSocketAddress();
        } catch (SocketTimeoutException ex) {
            return null;
        } catch (IOException e) {
            System.out.println("Адрес недоступен!");
            throw new RuntimeException(e);
        }

        Message message = extractMessage(packet.getData());
        return message;
    }

    private Message extractMessage(byte[] bytes) {
        Message msg = new Message(bytes);
        return msg;
    }


    private Message extractMessage(ByteBuffer buffer) {
        Message msg = null;
        try {
            msg = new Message(buffer);
        } catch (IOException e) {
            return null;
        }
        return msg;
    }
}
