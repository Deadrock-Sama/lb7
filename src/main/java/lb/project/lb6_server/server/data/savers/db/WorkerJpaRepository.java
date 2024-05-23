package lb.project.lb6_server.server.data.savers.db;

import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Hashtable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface WorkerJpaRepository extends JpaRepository<Worker, Integer> {
    List<Worker> findByNameLike(String substring);

    default Hashtable<Integer, Worker> findAllAsHashtable() {
        return new Hashtable<Integer, Worker>(findAll()
                .stream()
                .collect(Collectors.toMap(e -> e.getHashtableKey(), e -> e)));
    }
}
