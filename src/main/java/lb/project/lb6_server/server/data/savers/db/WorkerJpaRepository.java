package lb.project.lb6_server.server.data.savers.db;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface WorkerJpaRepository  extends JpaRepository<Worker, Integer> {
    List<Worker> findByNameLike(String substring);

    void deleteAllByOwner(User owner);
    void deleteAllByHashtableKeyGreaterThan(int key);

    default Hashtable<Integer, Worker> findAllAsHashtable() {
        return new Hashtable<Integer, Worker>(findAll()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(e -> e.getHashtableKey(), e -> e)));
    }


}
