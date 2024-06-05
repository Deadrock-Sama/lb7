package lb.project.lb6_server.server.data;

import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.entities.Worker;

import java.util.List;


public interface IWorkersRepository {

    String info();

    boolean insert(int key, Worker worker, User user);

    boolean update(int key, Worker worker, User user);

    boolean remove(int key, User user);

    void clear(User user);

    List<Worker> selectAll();

    void removeLowerWorkers(Worker worker, User user);

    boolean replaceWithGreaterWorker(int key, Worker worker, User user);

    void removeWorkersWithGreaterKey(int key, User user);

    List<Worker> selectWorkersWithSubstringInName(String substring);

    int getCountOfWorkersWithLessPerson(Person person);

    List<Worker> sorted();


}
