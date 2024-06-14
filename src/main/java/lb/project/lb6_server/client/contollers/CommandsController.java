package lb.project.lb6_server.client.contollers;

import lb.project.lb6_server.client.commands.Command;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class CommandsController implements ICommandsController{

    public Command getCommand(String input) {

        user = authorizationCommands.values()
                .stream()
                .map(pair -> pair.getUser())
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);


        Map<String, Command> availableCommands = (user == null) ? authorizationCommands : commands;

        Command empty = availableCommands.get("empty");
        if (input != null) {
            Command command = availableCommands.getOrDefault(input.toLowerCase(), empty);
            if (command != null)
                command.setUser(user);

            return command;
        }
        return empty;
    }

    @Autowired
    private Map<String, Command> commands;

    @Autowired
    @Qualifier("Authorization")
    private Map<String, Command> authorizationCommands;

    private User user;

}
