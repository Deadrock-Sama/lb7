package lb.project.lb6_server.client.commands;


import lb.project.lb6_server.client.builders.WorkerBuilder;
import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.stereotype.Component;

@Component("remove_lower")
public class RemoveLower extends DataManageCommand {


    @Override
    public boolean exexute() {

        Worker worker = new WorkerBuilder(getUiController())
                .setRequiredFields()
                .build();

        Message message = new Message("remove_lower", worker);
        return getExchangeChannel().sendMesssage(message);

    }
}
