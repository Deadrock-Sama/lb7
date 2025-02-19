package lb.project.lb6_server.client.builders;


import lb.project.lb6_server.lib.entities.Location;
import lb.project.lb6_server.lib.ui.UIController;

public class LocationBuilder extends Builder {

    public LocationBuilder(UIController controller) {
        super(controller);
    }

    public LocationBuilder setRequiredFields() {

        setName();
        setX();
        setY();
        setZ();

        return this;

    }

    public Location build() {

        if (isVaild())
            return new Location(x, y, z, name);

        controller.show("Значения " + this + " недопустимы");
        return null;

    }

    @Override
    public String toString() {

        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';

    }

    private LocationBuilder setX() {

        x = controller.readInteger("Введите x: ");
        return this;

    }

    private LocationBuilder setY() {

        y = null;
        while (y == null) {
            controller.show("Значение y не должно быть null", false);
            y = controller.readLong("Введите y: ");
        }
        return this;

    }

    private LocationBuilder setZ() {

        z = null;
        while (z == null) {
            controller.show("Значение z не должно быть null", false);
            z = controller.readFloat("Введите z: ");
        }
        return this;

    }


    private LocationBuilder setName() {

        name = null;
        while (name == null || name.length() > 966) {
            controller.show("Длина имени не должна быть больше 966. Имя не должно быть null.", false);
            name = controller.readString("Введите имя: ");
        }
        return this;

    }


    private boolean isVaild() {
        return (z != null && y != null && (name != null ? name.length() <= 966 : true));
    }

    private int x;
    private Long y;
    private Float z;
    private String name;

}
