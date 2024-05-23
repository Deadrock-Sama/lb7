package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

@Component("remove_key")
public class RemoveKey extends DataManageCommand {

    @Override
    public boolean exexute() {
        int key = getUiController().readInteger("Введите ключ: ");
        Message message = new Message("remove_key", key, getUser());
        return getExchangeChannel().sendMesssage(message);

    }
}
