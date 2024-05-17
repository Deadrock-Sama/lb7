package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("remove_lower")
public class RemoveLower extends DataManageCommand {

    @Override
    public boolean exexute(Serializable worker) {

        getWorkersRepository().removeLowerWorkers((Worker)worker);
        return true;
    }

}
