package lb.project.lb6_server.client.commands;


import lb.project.lb6_server.client.builders.WorkerBuilder;
import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("remove_lower")

public class RemoveLower extends DataManageCommand {


    @Override
    public boolean exexute() {

        Worker worker = new WorkerBuilder(getUiController())
                .setRequiredFields()
                .build();

        worker.setOwner(getUser());
        
        Message message = new Message("remove_lower", worker, getUser());
        return getExchangeChannel().sendMesssage(message);

    }
}
