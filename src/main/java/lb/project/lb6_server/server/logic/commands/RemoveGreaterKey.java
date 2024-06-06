package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;

@Component("remove_greater_key")
public class RemoveGreaterKey extends DataManageCommand {

    @Override
    public boolean exexute(Serializable key, User user, SocketAddress address) {
        getWorkersRepository().removeWorkersWithGreaterKey((Integer)key, user);
        return true;
    }

}
