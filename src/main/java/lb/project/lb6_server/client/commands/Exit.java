package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

@Component("exit")
public class Exit extends Command {
    @Override
    public boolean exexute() {
        Message message = new Message("save", null);
        getExchangeChannel().sendMesssage(message);
        System.exit(0);
        return false;
    }
}
