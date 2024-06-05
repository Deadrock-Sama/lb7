package lb.project.lb6_server.lib.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "userDB")
@Table(name = "usersDB")
public class User implements Serializable {

    private String password;
    private String login;
    @Id
    @GeneratedValue
    private Integer id;


    public User(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password) && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, login);
    }
}
