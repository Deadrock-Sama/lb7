package lb.project.lb6_server.lib.senders;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientExchangeChannel implements IExchangeChannel {

    private SocketAddress target;
    private DatagramChannel channel;

    public ClientExchangeChannel(SocketAddress target, SocketAddress host) {
        this(host);
        this.target = target;
    }

    public ClientExchangeChannel(SocketAddress host) {
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

    public Message receiveMessage() {
        byte[] bytes = new byte[4000];
        DatagramPacket packet;
        try {
            packet = new DatagramPacket(bytes, 4000);
            channel.socket().receive(packet);
            target = packet.getSocketAddress();
        } catch (SocketTimeoutException ex) {
            System.out.println("Адрес недоступен!");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Message message = extractMessage(packet.getData());
        return message;
    }


    private Message extractMessage(byte[] bytes) {
        Message msg = new Message(bytes);
        return msg;
    }

}
