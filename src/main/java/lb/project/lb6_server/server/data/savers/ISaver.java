package lb.project.lb6_server.server.data.savers;

import lb.project.lb6_server.server.data.IWorkersRepository;
public interface ISaver {

    void save(IWorkersRepository repository);

    IWorkersRepository load();


}
