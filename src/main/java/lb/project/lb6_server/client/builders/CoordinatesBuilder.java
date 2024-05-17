package lb.project.lb6_server.client.builders;


import lb.project.lb6_server.lib.entities.Coordinates;
import lb.project.lb6_server.lib.ui.UIController;

public class CoordinatesBuilder extends Builder {

    public CoordinatesBuilder(UIController controller) {
        super(controller);
    }

    public CoordinatesBuilder setRequiredFields() {

        setX();
        setY();

        return this;

    }

    public Coordinates build() {

        if (isValid())
            return new Coordinates(x, y);

        controller.show("Недопустимые значения: " + this);
        return null;

    }

    @Override
    public String toString() {

        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';

    }

    private CoordinatesBuilder setX() {

        x = -700;
        while (x <= -678) {
            controller.show("Значение x должно быть больше -678", false);
            x = controller.readDouble("Введите x: ");
        }
        return this;

    }

    private CoordinatesBuilder setY() {

        y = null;
        while (y == null) {
            controller.show("Значение y не должно быть null", false);
            y = controller.readLong("Введите y: ");
        }
        return this;

    }

    private boolean isValid() {
        return (x > -678 && y != null);
    }

    private double x = (-700);
    private Long y;


}
