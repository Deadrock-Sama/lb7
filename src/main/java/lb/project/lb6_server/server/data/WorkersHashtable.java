package lb.project.lb6_server.server.data;

import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.entities.Worker;
import org.hibernate.sql.ast.tree.expression.Collation;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class WorkersHashtable implements IWorkersRepository {

    private Map<Integer, Worker> workers = Collections.synchronizedMap(new Hashtable<Integer, Worker>());
    private LocalDateTime initDate = LocalDateTime.now();

    public WorkersHashtable(Map<Integer, Worker> workers) {
        this.workers = Collections.synchronizedMap(workers);
    }

    public WorkersHashtable() {
    }

    @Override
    public String info() {

        StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append("Дата инициализации: " + initDate + "\n");
        infoBuilder.append("Кол-во элементов: " + workers.size() + "\n");
        infoBuilder.append("Тип коллекции: " + workers.getClass() + "\n");

        return infoBuilder.toString();
    }

    @Override
    public boolean insert(int key, Worker worker, User user) {
        if (workers.containsKey(key) && !workers.get(key).getOwner().equals(user))
            return false;

        workers.put(key, worker);
        worker.setHashtableKey(key);
        return true;
    }

    @Override
    public boolean update(int key, Worker worker, User user) {

        Worker workerToUpdate = workers.get(key);

        if(!workerToUpdate.getOwner().equals(user))
            return false;

        workerToUpdate.copyData(worker);
        return true;
    }

    @Override
    public boolean remove(int key, User user)
    {
        if(!(workers.containsKey(key) && workers.get(key).getOwner().equals(user)))
            return false;

        workers.remove(key);
        return true;
    }

    @Override
    public void clear(User user) {
        workers = new Hashtable<>(workers
                .entrySet()
                .stream()
                .filter(e -> (!e.getValue().getOwner().equals(user)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @Override
    public List<Worker> selectAll() {
        return workers
                .values()
                .stream()
                .toList();
    }

    @Override
    public void removeLowerWorkers(Worker worker, User user) {
        workers = new Hashtable<>(workers
                .entrySet()
                .stream()
                .filter(e -> (!e.getValue().getOwner().equals(user)))
                .filter(e -> (e.getValue().compareTo(worker) > 0))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
    }

    public List<Worker> getLowerWorkersByUser(Worker worker, User user) {
        return workers
                .entrySet()
                .stream()
                .map(e -> e.getValue())
                .filter(e -> (e.compareTo(worker) <= 0))
                .filter(e -> (e.getOwner().equals(user)))
                .toList();
    }


    @Override
    public boolean replaceWithGreaterWorker(int key, Worker worker, User user) {
        if (workers.containsKey(key)
                && workers.get(key).compareTo(worker) > 0
                && workers.get(key).getOwner().equals(user))
        {workers.put(key, worker); return true; }
        return false;
    }

    @Override
    public void removeWorkersWithGreaterKey(int key, User user) {
        workers = new Hashtable<>(workers
                .entrySet()
                .stream()
                .filter(e -> (!e.getValue().getOwner().equals(user)))
                .filter(e -> (e.getKey().compareTo(key) <= 0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public List<Worker> getListOfWorkersWithGreaterKeyByUser(int key, User user) {
        return workers.entrySet()
                .stream()
                .filter(e -> (e.getKey().compareTo(key) > 0))
                .filter(e -> (e.getValue().getOwner().equals(user)))
                .map(e -> e.getValue())
                .toList();
    }

    @Override
    public List<Worker> selectWorkersWithSubstringInName(String substring) {

        return workers
                .values()
                .stream()
                .filter(e -> (e.getName().contains(substring)))
                .toList();
    }

    @Override
    public int getCountOfWorkersWithLessPerson(Person person) {
        return (int) workers
                .values()
                .stream()
                .filter(e -> (e.getPerson().compareTo(person) > 0))
                .count();
    }

    @Override
    public List<Worker> sorted() {

        return workers.values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();

    }

    @Override
    public void save() {
        //save if need file
    }

    @Override
    public void load() {

    }

    public Worker getByKey(int key) {
        return workers.get(key);
    }
}
