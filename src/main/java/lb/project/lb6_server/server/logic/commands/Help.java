package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("help")
public class Help extends Command {
    @Override
    public boolean exexute(Serializable entity) {

        UIController controller = getUiController();
        controller.show("save : сохранить коллекцию в файл");
        return true;
    }
}
