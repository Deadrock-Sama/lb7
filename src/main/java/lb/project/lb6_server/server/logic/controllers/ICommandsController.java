package lb.project.lb6_server.server.logic.controllers;

import lb.project.lb6_server.server.logic.commands.Command;

public interface ICommandsController {
    public Command getCommand(String input);

}
