package lb.project.lb6_server.client.contollers;


import lb.project.lb6_server.client.commands.Command;

public interface ICommandsController {
    public Command getCommand(String input);

}
