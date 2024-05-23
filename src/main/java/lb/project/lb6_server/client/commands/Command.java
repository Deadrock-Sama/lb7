package lb.project.lb6_server.client.commands;


import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.senders.ExchangeChannel;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


abstract public class Command {

    public UIController getUiController() {
        return uiController;
    }

    public ExchangeChannel getExchangeChannel() { return exchangeChannel; }

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
    private ExchangeChannel exchangeChannel;

    private User user;

}
