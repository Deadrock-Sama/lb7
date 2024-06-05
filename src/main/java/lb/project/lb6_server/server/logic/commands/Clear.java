package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.server.logic.aspects.AuthorizationCheck;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("clear")
@AuthorizationCheck
public class Clear extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity, User user) {
        getWorkersRepository().clear(user);
        return true;
    }
}
