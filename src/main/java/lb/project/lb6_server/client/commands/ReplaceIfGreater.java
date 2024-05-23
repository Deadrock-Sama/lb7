package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.client.builders.WorkerBuilder;
import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.lib.messages.KeyValuePair;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

@Component("replace_if_greater")
public class ReplaceIfGreater extends DataManageCommand {

    @Override
    public boolean exexute() {

        int key = getUiController().readInteger("Введите ключ: ");
        Worker worker = new WorkerBuilder(getUiController())
                .setRequiredFields()
                .build();

        KeyValuePair pair = new KeyValuePair(key, worker);
        Message message = new Message("replace_if_greater", pair, getUser());

        return getExchangeChannel().sendMesssage(message);
    }
}
