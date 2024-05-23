package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("exit")
public class Exit extends Command {
    @Override
    public boolean exexute(Serializable entity, User user) {
        System.exit(0);
        return false;
    }
}
