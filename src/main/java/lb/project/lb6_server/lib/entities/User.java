package lb.project.lb6_server.lib.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
@Entity
public class User implements Serializable {

    private byte[] password;
    private String login;
    @Id
    @GeneratedValue
    private Long id;


    public User(String login, byte[] password) {
        this.password = password;
        this.login = login;
    }

    public User() {

    }

    public byte[] getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
