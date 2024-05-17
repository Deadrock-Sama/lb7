package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.lib.messages.Message;
import lb.project.lb6_server.lib.entities.Person;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("count_less_than_person")
public class CountLessThanPerson extends DataManageCommand {

    @Override
    public boolean exexute(Serializable entity) {
        Person person = (Person)entity;
        int count = getWorkersRepository().getCountOfWorkersWithLessPerson(person);
        Message message = new Message("count_less_than_person", count);
        return  getExchangeChannel().sendMesssage(message);
    }


}
