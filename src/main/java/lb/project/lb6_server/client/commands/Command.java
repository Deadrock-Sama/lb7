package lb.project.lb6_server.client.commands;


import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.senders.ClientExchangeChannel;
import lb.project.lb6_server.lib.senders.IExchangeChannel;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


abstract public class Command {

    public UIController getUiController() {
        return uiController;
    }

    public ClientExchangeChannel getExchangeChannel() { return exchangeChannel; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public abstract boolean exexute();

    @Autowired
    @Qualifier("ConsoleController")
    private UIController uiController;


    @Autowired
    @Qualifier("ClientChannel")
    private ClientExchangeChannel exchangeChannel;

    private User user;

}
