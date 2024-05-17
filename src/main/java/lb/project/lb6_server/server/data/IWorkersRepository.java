package lb.project.lb6_server.server.data;

import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.entities.Worker;

import java.util.List;


public interface IWorkersRepository {

    String info();

    void insert(int key, Worker worker);

    void update(int key, Worker worker);

    void remove(int key);

    void clear();

    List<Worker> selectAll();

    void removeLowerWorkers(Worker worker);

    void replaceWithGreaterWorker(int key, Worker worker);

    void removeWorkersWithGreaterKey(int key);

    List<Worker> selectWorkersWithSubstringInName(String substring);

    int getCountOfWorkersWithLessPerson(Person person);

    List<Worker> sorted();


}
