package lb.project.lb6_server.client.commands;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("execute_script")
public class ExecuteScript extends Command {

    @Override
    public boolean exexute() {

        String filePath = getUiController().readString("Введите путь к файлу: ").replace("\\", "\\\\");
        getUiController().setFileToRead(filePath);

        return true;

    }
}
