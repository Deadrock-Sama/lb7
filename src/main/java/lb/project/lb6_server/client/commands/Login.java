package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.client.builders.UserBuilder;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("login")
@Qualifier("Authorization")
public class Login extends Command {
    @Override
    public boolean exexute() {

        if (getUser() != null) {
            getUiController().show("Вы уже авторизованы!");
            return false;
        }

        User user = new UserBuilder(getUiController())
                .setLogin()
                .setPassword()
                .build();

        Message message = new Message("login", user);
        if (!getExchangeChannel().sendMesssage(message)) {
            getUiController().show("Ошибка при отправке запроса на сервер!");
            return false;
        }

        Message response = getExchangeChannel().recieveMessageWithTimeOut();
        if (response == null)
            return false;

        if ((boolean)response.getEntity()) {
            setUser(user);
            return true;
        }

        return false;
    }
}
