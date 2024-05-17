package lb.project.lb6_server.client.contollers;

import lb.project.lb6_server.client.commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandsController implements ICommandsController{

    public Command getCommand(String input) {
        Command empty = commands.get("empty");
        if (input != null)
            return commands.getOrDefault(input.toLowerCase(), empty);

        return empty;
    }

    @Autowired
    private Map<String, Command> commands;

}
