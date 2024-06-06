package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.SocketAddress;

@Component("empty")
@Qualifier("ServerCommand")
public class EmptyCommand extends Command {
    @Override
    public boolean exexute(Serializable entity, User user, SocketAddress address) {
        getUiController().show("Введена неверная команда. Повторите попытку.");
        return false;
    }
}
