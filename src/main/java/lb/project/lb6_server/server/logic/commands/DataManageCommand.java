package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.server.data.IWorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataManageCommand extends Command {

    protected IWorkersRepository getWorkersRepository() {
        return workersRepository;
    }

    @Autowired
    private IWorkersRepository workersRepository;
}