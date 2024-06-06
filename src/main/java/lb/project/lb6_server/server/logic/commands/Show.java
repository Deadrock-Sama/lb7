package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;

@Component("show")
public class Show extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity, User user, SocketAddress address)
    {
        Message message = new Message("show", (Serializable)getWorkersRepository().selectAll());
        return getExchangeChannel().sendMesssage(message, address);
    }
}
