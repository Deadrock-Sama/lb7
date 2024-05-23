package lb.project.lb6_server.server.data;

import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.entities.Worker;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkersHashtable implements IWorkersRepository {

    private Map<Integer, Worker> workers = new Hashtable<Integer, Worker>();
    private LocalDateTime initDate = LocalDateTime.now();

    public WorkersHashtable(Map<Integer, Worker> workers) {
        this.workers = workers;
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
    public void insert(int key, Worker worker) {
        workers.put(key, worker);
    }

    @Override
    public void update(int key, Worker worker) {

        Worker workerToUpdate = workers.get(key);
        workerToUpdate.copyData(worker);

    }

    @Override
    public void remove(int key) {
        workers.remove(key);
    }

    @Override
    public void clear() {
        workers.clear();
    }

    @Override
    public List<Worker> selectAll() {
        return workers
                .values()
                .stream()
                .toList();
    }

    @Override
    public void removeLowerWorkers(Worker worker) {
        workers = new Hashtable<>(workers
                .entrySet()
                .stream()
                .filter(e -> (e.getValue().compareTo(worker) > 0))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
    }

    public List<Worker> getLowerWorkers(Worker worker) {
        return workers
                .entrySet()
                .stream()
                .map(e -> e.getValue())
                .filter(e -> (e.compareTo(worker) <= 0))
                .toList();
    }


    @Override
    public void replaceWithGreaterWorker(int key, Worker worker) {
        if (workers.containsKey(key) && workers.get(key).compareTo(worker) > 0)
            workers.put(key, worker);
    }

    @Override
    public void removeWorkersWithGreaterKey(int key) {
        workers = new Hashtable<>(workers
                .entrySet()
                .stream()
                .filter(e -> (e.getKey().compareTo(key) <= 0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public List<Worker> getListOfWorkersWithGreaterKey(int key) {
        return workers.entrySet()
                .stream()
                .filter(e -> (e.getKey().compareTo(key) > 0))
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

    public Worker getByKey(int key) {
        return workers.get(key);
    }
}
