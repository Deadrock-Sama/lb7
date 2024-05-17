package lb.project.lb6_server.server.logic.controllers;

import lb.project.lb6_server.server.logic.commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public Command getServerCommand(String input) {
        Command empty = serverCommands.get("empty");
        if (input != null)
            return serverCommands.getOrDefault(input.toLowerCase(), empty);

        return empty;
    }

    @Autowired
    private Map<String, Command> commands;

    @Autowired
    @Qualifier("ServerCommand")
    private Map<String, Command> serverCommands;
}
