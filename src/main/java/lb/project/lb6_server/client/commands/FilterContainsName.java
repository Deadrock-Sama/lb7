package lb.project.lb6_server.client.commands;


import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("filter_contains_name")
public class FilterContainsName extends DataManageCommand {

    @Override
    public boolean exexute() {

        String substring = getUiController().readString("Введите подстроку: ");

        Message message = new Message("filter_contains_name", substring, getUser());
        if (!getExchangeChannel().sendMesssage(message)) {
            getUiController().show("Ошибка при отправке запроса на сервер!");
            return false;
        }

        Message response = getExchangeChannel().recieveMessageWithTimeOut();
        if (response == null)
            return false;
        List<Worker> workerList = (List<Worker>)response.getEntity();
        getUiController().show(workerList.toString());

        return true;
    }
}
