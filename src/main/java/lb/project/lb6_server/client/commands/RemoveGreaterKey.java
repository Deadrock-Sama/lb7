package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("remove_greater_key")

public class RemoveGreaterKey extends DataManageCommand {

    @Override
    public boolean exexute() {
        int key = getUiController().readInteger("Введите клюс: ");
        Message message = new Message("remove_greater_key", key, getUser());
        return getExchangeChannel().sendMesssage(message);
    }

}
