package lb.project.lb6_server.client.commands;


import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

@Component("info")
public class Info extends DataManageCommand {

    @Override
    public boolean exexute() {

        Message message = new Message("info", null);
        if (!getExchangeChannel().sendMesssage(message)) {
            getUiController().show("Ошибка при отправке запроса на сервер!");
            return false;
        }

        Message response = getExchangeChannel().recieveMessageWithTimeOut();
        if (response == null)
            return false;
        String info = (String)response.getEntity();
        getUiController().show(info);
        return true;
    }
}
