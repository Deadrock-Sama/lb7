package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.server.data.savers.ISaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;

@Component("save")
@Qualifier("ServerCommand")
public class Save extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity, User user, SocketAddress address) {
        getWorkersRepository().save();
        return true;
    }
}
