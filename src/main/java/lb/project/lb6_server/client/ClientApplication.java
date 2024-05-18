package lb.project.lb6_server.client;


import lb.project.lb6_server.client.contollers.CommandsController;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClientApplication {

    @EventListener(ContextRefreshedEvent.class)
    public void start() {

        while (true) {

            boolean result = commandsController.getCommand(uiController.readString("Введите команду: ")).exexute();

            if (result)
                uiController.show("Команда успешно выполнена!", true);
            else
                uiController.show("Ошибка сервера! Попробуйте еще раз!");
        }

    }

    @Autowired
    private CommandsController commandsController;
    @Autowired
    private UIController uiController;

}
