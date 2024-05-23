package lb.project.lb6_server.server.data.savers.db;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    public boolean existsUserByLoginAndPassword(String login, byte[] password);

}
