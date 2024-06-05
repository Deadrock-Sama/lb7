package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("show")

public class Show extends DataManageCommand {

    @Override
    public boolean exexute() {
        Message message = new Message("show", getUser());

        if (!getExchangeChannel().sendMesssage(message)) {
            getUiController().show("Ошибка при отправке запроса на сервер!");
            return false;
        }

        Message response = getExchangeChannel().recieveMessageWithTimeOut();
        if (response == null)
            return false;

        List<Worker> workers = (List<Worker>)response.getEntity();
        getUiController().show(workers.toString());
        return true;
    }
}
