package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.KeyValuePair;
import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("replace_if_greater")
public class ReplaceIfGreater extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity, User user) {

        KeyValuePair kvp = (KeyValuePair)entity;
        getWorkersRepository().replaceWithGreaterWorker(kvp.getKey(), (Worker) kvp.getValue());

        return true;
    }
}
