package lb.project.lb6_server.lib.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class Location implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Location(int x, Long y, Float z, String name) {
        this.x = x;
        this.y = Objects.requireNonNull(y, "y must not be null");
        this.z = Objects.requireNonNull(z, "z must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");

        if (name.length() >= 966)
            throw new IllegalArgumentException("Name can't be longer than 966 chars");


    }

    private int x;
    private Long y;
    private Float z;
    private String name;

    public Location() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}