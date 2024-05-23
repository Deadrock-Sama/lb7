package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.KeyValuePair;
import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.server.logic.aspects.AuthorizationCheck;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("insert")
public class Insert extends DataManageCommand {
    @Override
    @AuthorizationCheck
    public boolean exexute(Serializable entity, User user) {

        KeyValuePair kvp = (KeyValuePair)entity;
        getWorkersRepository().insert(kvp.getKey(), (Worker)kvp.getValue());
        return true;
    }
}
