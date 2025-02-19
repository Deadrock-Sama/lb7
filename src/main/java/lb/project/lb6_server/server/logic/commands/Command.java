package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.senders.ServerExchangeChannel;
import lb.project.lb6_server.lib.ui.UIController;
import lb.project.lb6_server.server.data.savers.db.UserJpaRepository;
import lb.project.lb6_server.server.logic.aspects.AuthorizationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.net.SocketAddress;

@AuthorizationCheck
abstract public class Command {

    public UIController getUiController() {
        return uiController;
    }

    public ServerExchangeChannel getExchangeChannel() { return exchangeChannel; }

    public UserJpaRepository getUserJpaRepository() {
        return userJpaRepository;
    }

    public boolean isUserExists(User user) {
        return userJpaRepository.existsUserByLoginAndPassword(user.getLogin(), user.getPassword());
    }
    @AuthorizationCheck
    public abstract boolean exexute(Serializable entity, User user, SocketAddress address);


    @Autowired
    @Qualifier("ConsoleController")
    private UIController uiController;

    @Autowired
    @Qualifier("ServerChannel")
    private ServerExchangeChannel exchangeChannel;

    @Autowired
    private UserJpaRepository userJpaRepository;


}
