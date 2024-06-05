package lb.project.lb6_server.client.commands;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("empty")
@Qualifier("Authorization")
public class EmptyCommand extends Command {
    @Override
    public boolean exexute() {
        getUiController().show("Введена неверная команда. Повторите попытку.");
        return true;
    }
}
