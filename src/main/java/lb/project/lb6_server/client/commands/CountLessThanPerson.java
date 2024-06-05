package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.client.builders.PersonBuilder;
import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("count_less_than_person")
public class CountLessThanPerson extends DataManageCommand {

    @Override
    public boolean exexute() {

        Person person = new PersonBuilder(getUiController())
                .setRequiredFields()
                .build();

        Message message = new Message("count_less_than_person", person, getUser());
        if (!getExchangeChannel().sendMesssage(message)) {
            getUiController().show("Ошибка при отправке запроса на сервер!");
            return false;
        }

        Message response = getExchangeChannel().recieveMessageWithTimeOut();
        if (response == null)
            return false;
        int count = (Integer)response.getEntity();
        getUiController().show("Количество работников с меньшей персоной: " + count);

        return true;
    }
}
