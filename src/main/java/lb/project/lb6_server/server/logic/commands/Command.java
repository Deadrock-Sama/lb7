package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.senders.ExchangeChannel;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;


abstract public class Command {

    public UIController getUiController() {
        return uiController;
    }

    public ExchangeChannel getExchangeChannel() { return exchangeChannel; }

    public abstract boolean exexute(Serializable entity);


    @Autowired
    @Qualifier("ConsoleController")
    private UIController uiController;

    @Autowired
    @Qualifier("ServerChannel")
    private ExchangeChannel exchangeChannel;

}
