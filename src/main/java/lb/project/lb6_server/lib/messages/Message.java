package lb.project.lb6_server.lib.messages;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.server.logic.commands.Command;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Message implements Serializable {
    private String commandName;

    private Serializable entity;

    private User user;

    private SocketAddress sender;

    public Message(String commandName, Serializable entity, User user) {
        this(commandName, user);
        this.entity = entity;
    }

    public Message(String commandName, Serializable entity) {
        this.commandName = commandName;
        this.entity = entity;
    }

    public Message(String commandName, User user) {
        this.commandName = commandName;
        this.user = user;
    }

    public Message(Serializable entity, User user) {
        this.entity = entity;
        this.user = user;
    }



    public Message(ByteBuffer buffer) throws IOException {

        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        ObjectInputStream in = null;
        in = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = null;
        try {
            message = (Message)in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.entity = message.entity;
        this.commandName = message.commandName;
        this.user = message.user;

    }

    public Message(byte[] bytes) {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Message message = null;
        try {
            message = (Message)in.readObject();
        }
        catch (EOFException e) {
            message = null;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.entity = message.entity;
        this.commandName = message.commandName;
        this.user = message.user;
    }

    public Serializable getEntity() {
        return entity;
    }

    public String getCommandName() {
        return commandName;
    }

    public User getUser() { return user; }

    public SocketAddress getSender() {
        return sender;
    }

    public void setSender(SocketAddress sender) {
        this.sender = sender;
    }
}
