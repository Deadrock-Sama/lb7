package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("info")
public class Info extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity, User user) {
        Message message =  new Message("info", getWorkersRepository().info());

        return  getExchangeChannel().sendMesssage(message);
    }
}
