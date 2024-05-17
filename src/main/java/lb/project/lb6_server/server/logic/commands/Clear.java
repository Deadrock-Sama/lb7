package lb.project.lb6_server.server.logic.commands;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("clear")
public class Clear extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity) {
        getWorkersRepository().clear();
        return true;
    }
}
