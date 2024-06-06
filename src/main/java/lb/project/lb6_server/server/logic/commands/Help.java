package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;

@Component("help")
public class Help extends Command {
    @Override
    public boolean exexute(Serializable entity, User user, SocketAddress address) {

        UIController controller = getUiController();
        controller.show("save : сохранить коллекцию в файл");
        return true;
    }
}
