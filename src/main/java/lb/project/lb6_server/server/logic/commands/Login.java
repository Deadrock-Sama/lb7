package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;

import java.io.Serializable;

public class Login extends Command{
    @Override
    public boolean exexute(Serializable entity, User user) {

        boolean result = isUserExists((User)entity);
        Message message = new Message("login", result);

        return getExchangeChannel().sendMesssage(message);
    }
}
