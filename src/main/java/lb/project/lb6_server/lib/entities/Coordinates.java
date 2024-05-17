package lb.project.lb6_server.lib.entities;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {

    public Coordinates(double x, Long y) {
        this.y = Objects.requireNonNull(y, "Значение y не должно быть null");

        if (x < -678)
            throw new IllegalArgumentException("Значение x должно быть больше -678");
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
}