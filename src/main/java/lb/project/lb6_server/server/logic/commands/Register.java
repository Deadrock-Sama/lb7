package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;

@Component("register")
public class Register extends Command{
    @Override
    public boolean exexute(Serializable entity, User user, SocketAddress address) {

        getUserJpaRepository().saveAndFlush(user);
        Message message = new Message("register", true);

        return getExchangeChannel().sendMesssage(message, address);

    }
}
