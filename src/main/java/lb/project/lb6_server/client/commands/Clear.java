package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

@Component("clear")
public class Clear extends DataManageCommand {

    @Override
    public boolean exexute() {
        Message message = new Message("clear", getUser());
        return getExchangeChannel().sendMesssage(message);
    }
}
