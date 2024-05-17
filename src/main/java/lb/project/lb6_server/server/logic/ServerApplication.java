package lb.project.lb6_server.server.logic;

import lb.project.lb6_server.lib.senders.ExchangeChannel;
import lb.project.lb6_server.lib.ui.UIController;
import lb.project.lb6_server.server.logic.controllers.CommandsController;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ServerApplication {


    @EventListener(ContextRefreshedEvent.class)
    public void start() {

        Runnable listener = () -> {

            try {
                Message message = exchangeChannel.recieveMessage();
                commandsController.getCommand(message.getCommandName()).exexute(message.getEntity());
            } catch (Exception e) {

            }

        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(listener, 0, 3, TimeUnit.SECONDS);

      while (true) {
            boolean result = commandsController.getCommand(uiController.readString("Введите команду: ")).exexute(null);

            if (result)
               uiController.show("Команда успешно выполнена!", false);
        }

    }

    @Autowired
    @Qualifier("ServerChannel")
    private ExchangeChannel exchangeChannel;
    @Autowired
    private CommandsController commandsController;
    @Autowired
    private UIController uiController;
}