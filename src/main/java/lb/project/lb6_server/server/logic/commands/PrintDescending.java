package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("print_descending")
public class PrintDescending extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity) {

        Message message = new Message("print_descending", (Serializable)getWorkersRepository().sorted());
        return getExchangeChannel().sendMesssage(message);
    }

}
