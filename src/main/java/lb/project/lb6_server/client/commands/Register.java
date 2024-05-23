package lb.project.lb6_server.client.commands;

import lb.project.lb6_server.client.builders.UserBuilder;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.messages.Message;

public class Register extends Command{
    @Override
    public boolean exexute() {

        User user = new UserBuilder(getUiController())
                .setLogin()
                .setPassword()
                .repeatPassword();

        Message message = new Message("register", user);
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

        return getExchangeChannel().sendMesssage(message);
    }
}
