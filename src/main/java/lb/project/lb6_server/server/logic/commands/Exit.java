package lb.project.lb6_server.server.logic.commands;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("exit")
public class Exit extends Command {
    @Override
    public boolean exexute(Serializable entity) {
        System.exit(0);
        return false;
    }
}
