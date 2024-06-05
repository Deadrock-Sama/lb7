package lb.project.lb6_server.server.logic;

import lb.project.lb6_server.lib.senders.ExchangeChannel;
import lb.project.lb6_server.lib.senders.IExchangeChannel;
import lb.project.lb6_server.lib.senders.SynchronizedExchangeChannel;
import lb.project.lb6_server.lib.ui.UIController;
import lb.project.lb6_server.server.logic.controllers.CommandsController;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.web.OffsetScrollPositionArgumentResolver;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ServerApplication {


    @Autowired
    @Qualifier("ServerChannel")
    private SynchronizedExchangeChannel exchangeChannel;

    @Autowired
    private CommandsController commandsController;
    @Autowired
    private UIController uiController;
    @EventListener(ContextRefreshedEvent.class)
    public void start() {

        ForkJoinPool pool = ForkJoinPool.commonPool();

        Runnable console = () -> {
            while (true) {
                boolean result = commandsController.getCommand(uiController.readString("Введите команду: ")).exexute(null, null);

                if (result)
                    uiController.show("Команда успешно выполнена!", false);
            }
        };

        pool.execute(console);
        exchangeChannel.setCommandsController(commandsController);
        // Запускаем обработку запросов в отдельном потоке
        Runnable requestProcessor = () -> {
            exchangeChannel.processRequests();
        };

        pool.execute(requestProcessor);
        while (true) {}
    }
}