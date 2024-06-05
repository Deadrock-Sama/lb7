package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("remove_key")
public class RemoveKey extends DataManageCommand {

    @Override
    public boolean exexute(Serializable key, User user) {
        getWorkersRepository().remove((int)key, user);
        return true;
    }
}
