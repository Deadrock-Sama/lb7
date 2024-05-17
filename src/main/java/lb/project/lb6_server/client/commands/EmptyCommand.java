package lb.project.lb6_server.client.commands;

import org.springframework.stereotype.Component;

@Component("empty")
public class EmptyCommand extends Command {
    @Override
    public boolean exexute() {
        getUiController().show("Введена неверная команда. Повторите попытку.");
        return false;
    }
}
