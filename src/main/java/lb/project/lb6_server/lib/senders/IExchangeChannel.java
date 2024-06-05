package lb.project.lb6_server.lib.senders;

import lb.project.lb6_server.lib.messages.Message;

import java.net.SocketAddress;

public interface IExchangeChannel {

    public Message recieveMessageWithTimeOut();
    public boolean sendMesssage(Message message);
    public Message recieveMessage();

}
