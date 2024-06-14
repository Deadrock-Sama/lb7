package lb.project.lb6_server.client;


import lb.project.lb6_server.client.contollers.CommandsController;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Component
public class ClientApplication {

    @EventListener(ContextRefreshedEvent.class)
    public void start()  {
        uiController.show("Команды авторизации: login/register");
        while (true) {

            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean result = commandsController.getCommand(uiController.readString("Введите команду: ")).exexute();

            if (result)
                uiController.show("Команда успешно выполнена!", false);

        }

    }

    @Autowired
    private CommandsController commandsController;
    @Autowired
    private UIController uiController;

}
