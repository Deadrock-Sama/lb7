package lb.project.lb6_server.server.logic.commands;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("remove_key")
public class RemoveKey extends DataManageCommand {

    @Override
    public boolean exexute(Serializable key) {
        getWorkersRepository().remove((int)key);
        return true;
    }
}
