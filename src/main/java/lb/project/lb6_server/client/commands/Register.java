package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.client.builders.UserBuilder;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("register")
@Qualifier("Authorization")
public class Register extends Command{
    @Override
    public boolean exexute() {

        if (getUser() != null) {
            getUiController().show("Вы уже авторизованы!");
            return false;
        }

        User user = new UserBuilder(getUiController())
                .setLogin()
                .setPassword()
                .repeatPassword();

        Message message = new Message("register", null, user);
        if (!getExchangeChannel().sendMesssage(message)) {
            getUiController().show("Ошибка при отправке запроса на сервер!");
            return false;
        }

        Message response = getExchangeChannel().receiveMessage();
        if (response == null)
            return false;

        if ((boolean)response.getEntity()) {
            setUser(user);
            getUiController().show("Вы успешно зарегестрированы");
            return false;
        }

        return false;

    }
}
