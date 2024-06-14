package lb.project.lb6_server.server.logic.commands;

import lb.project.lb6_server.server.data.IWorkersRepository;
import lb.project.lb6_server.server.data.WorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataManageCommand extends Command {

    protected IWorkersRepository getWorkersRepository() {

        if (!repositoryWasLoaded) {
            workersRepository.load();
            repositoryWasLoaded = true;
        }

        return workersRepository;
    }

    @Autowired
    private IWorkersRepository workersRepository;

    private boolean repositoryWasLoaded = false;
}