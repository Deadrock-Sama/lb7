package lb.project.lb6_server.server.data.savers.db;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

     boolean existsUserByLoginAndPassword(String login, String password);
     
     boolean existsUserByIdBetween(int id1, int id2);

}
