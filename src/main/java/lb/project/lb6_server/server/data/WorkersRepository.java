package lb.project.lb6_server.server.data;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.entities.Worker;
import lb.project.lb6_server.server.data.savers.db.WorkerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class WorkersRepository implements IWorkersRepository{

    @Autowired
    private WorkerJpaRepository jpaRepository;

    private WorkersHashtable hashtable;
    public void load() {
        hashtable = new WorkersHashtable(jpaRepository.findAllAsHashtable());
    }

    @Override
    public String info() {
        return hashtable.info();
    }

    @Override
    public boolean insert(int key, Worker worker, User user) {
        Worker currentWorker = hashtable.getByKey(key);
        if(!hashtable.insert(key, worker, user))
            return false;

        if(currentWorker != null)
            jpaRepository.delete(currentWorker);

        jpaRepository.saveAndFlush(worker);
        return true;
    }

    @Override
    public boolean update(int key, Worker worker, User user) {

        if(!hashtable.update(key, worker, user))
            return false;

        jpaRepository.saveAndFlush(hashtable.getByKey(key));//check
        return true;
    }

    @Override
    public boolean remove(int key, User user) {
        if (!hashtable.remove(key, user))
            return false;

        jpaRepository.delete(hashtable.getByKey(key));
        jpaRepository.flush();
        return true;
    }

    @Override
    public void clear(User user) {
        hashtable.clear(user);
        jpaRepository.deleteAllByOwner(user);
        jpaRepository.flush();
    }

    @Override
    public List<Worker> selectAll() {
        return hashtable.selectAll();
    }

    @Override
    public void removeLowerWorkers(Worker worker, User user) {
        List<Worker> workersToRemove = hashtable.getLowerWorkersByUser(worker, user);
        hashtable.removeLowerWorkers(worker, user);
        jpaRepository.deleteAll(workersToRemove);
        jpaRepository.flush();
    }

    @Override
    public boolean replaceWithGreaterWorker(int key, Worker worker, User user) {
        Worker currentWorker = hashtable.getByKey(key);
        if (currentWorker.compareTo(worker) > 0) {
            if(!hashtable.insert(key, worker, user))
                return false;

            jpaRepository.delete(currentWorker);
            jpaRepository.saveAndFlush(worker);

            return true;
        }
        return false;
    }

    @Override
    public void removeWorkersWithGreaterKey(int key, User user) {
        hashtable.removeWorkersWithGreaterKey(key, user);
        jpaRepository.deleteAllByHashtableKeyGreaterThan(key);
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

    @Override
    public void save() {
        jpaRepository.flush();
    }
}
