package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("empty")
@Qualifier("ServerCommand")
public class EmptyCommand extends Command {
    @Override
    public boolean exexute(Serializable entity, User user) {
        getUiController().show("Введена неверная команда. Повторите попытку.");
        return false;
    }
}
