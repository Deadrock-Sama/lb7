package lb.project.lb6_server.lib.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class Coordinates implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Coordinates(double x, Long y) {
        this.y = Objects.requireNonNull(y, "Значение y не должно быть null");

        if (x < -678)
            throw new IllegalArgumentException("Значение x должно быть больше -678");
    }

    public Coordinates() {

    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    private double x;
    private Long y;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}