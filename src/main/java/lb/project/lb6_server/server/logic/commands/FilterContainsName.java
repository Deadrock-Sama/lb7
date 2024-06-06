package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;
import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.List;

@Component("filter_contains_name")
public class FilterContainsName extends DataManageCommand {

    @Override
    public boolean exexute(Serializable substring, User user, SocketAddress address) {

        List<Worker> workerList = getWorkersRepository().selectWorkersWithSubstringInName((String)substring);
        Message message = new Message("filter_contains_name", (Serializable)workerList);

        return getExchangeChannel().sendMesssage(message, address);
    }
}
