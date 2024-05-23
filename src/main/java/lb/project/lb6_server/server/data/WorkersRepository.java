package lb.project.lb6_server.server.data;

import jakarta.annotation.PostConstruct;
import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.server.data.savers.db.WorkerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkersRepository implements IWorkersRepository{

    @Autowired
    private WorkerJpaRepository jpaRepository;

    private WorkersHashtable hashtable;
    @PostConstruct
    private void loadHashtable() {
        hashtable = new WorkersHashtable(jpaRepository.findAllAsHashtable());
    }

    @Override
    public String info() {
        return hashtable.info();
    }

    @Override
    public void insert(int key, Worker worker) {
        hashtable.insert(key, worker);
        jpaRepository.saveAndFlush(worker);
    }

    @Override
    public void update(int key, Worker worker) {

        if(!worker.getOwner().equals(hashtable.getByKey(key).getOwner()))
            return;

        hashtable.update(key, worker);
        jpaRepository.saveAndFlush(hashtable.getByKey(key));//check
    }

    @Override
    public void remove(int key) {
        hashtable.remove(key);
        jpaRepository.delete(hashtable.getByKey(key));
        jpaRepository.flush();
    }

    @Override
    public void clear() {
        hashtable.clear();
        jpaRepository.deleteAll();
        jpaRepository.flush();
    }

    @Override
    public List<Worker> selectAll() {
        return hashtable.selectAll();
    }

    @Override
    public void removeLowerWorkers(Worker worker) {
        List<Worker> workersToRemove = hashtable.getLowerWorkers(worker);
        hashtable.removeLowerWorkers(worker);
        jpaRepository.deleteAll(workersToRemove);
        jpaRepository.flush();
    }

    @Override
    public void replaceWithGreaterWorker(int key, Worker worker) {
        Worker currentWorker = hashtable.getByKey(key);
        if (currentWorker.compareTo(worker) > 0) {
            hashtable.insert(key, worker);
            jpaRepository.delete(currentWorker);
            jpaRepository.saveAndFlush(worker);
        }
    }

    @Override
    public void removeWorkersWithGreaterKey(int key) {
        List<Worker> workers = hashtable.getListOfWorkersWithGreaterKey(key);
        hashtable.removeWorkersWithGreaterKey(key);
        jpaRepository.deleteAll(workers);
        jpaRepository.flush();

    }

    @Override
    public List<Worker> selectWorkersWithSubstringInName(String substring) {
        return hashtable.selectWorkersWithSubstringInName(substring);
    }

    @Override
    public int getCountOfWorkersWithLessPerson(Person person) {
        return hashtable.getCountOfWorkersWithLessPerson(person);
    }

    @Override
    public List<Worker> sorted() {
        return hashtable.sorted();
    }
}
