package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;

import java.io.Serializable;

public class Register extends Command{
    @Override
    public boolean exexute(Serializable entity, User user) {

        getUserJpaRepository().saveAndFlush(user);
        Message message = new Message("register", true);

        return getExchangeChannel().sendMesssage(message);

    }
}
