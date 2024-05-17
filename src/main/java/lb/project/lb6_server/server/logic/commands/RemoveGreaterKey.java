package lb.project.lb6_server.server.logic.commands;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("remove_greater_key")
public class RemoveGreaterKey extends DataManageCommand {

    @Override
    public boolean exexute(Serializable key) {
        getWorkersRepository().removeWorkersWithGreaterKey((Integer)key);
        return true;
    }

}
