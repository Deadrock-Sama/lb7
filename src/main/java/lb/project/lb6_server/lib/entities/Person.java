package lb.project.lb6_server.lib.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "person",schema="s409677")
public class Person implements Comparable<Person>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personGen")
    @SequenceGenerator(name = "personGen", sequenceName = "person_id_seq", allocationSize = 1, schema = "s409677")
    private Long id;

    public Person(ZonedDateTime birthday, Double height, Location location, String passportID) {

        this.birthday = Objects.requireNonNull(birthday, "birthday must not be null");
        this.height = Objects.requireNonNull(height, "height must not be null");
        this.location = Objects.requireNonNull(location, "location must not be null");
        this.passportID = Objects.requireNonNull(passportID, "passportID must not be null");

        if (height.compareTo(0.0) <= 0)
            throw new IllegalArgumentException("height can't be <= 0");

        if (passportID.length() > 46)
            throw new IllegalArgumentException("passportID length can't be >46");

        this.passportID = UUID.randomUUID().toString();
    }

    public Person() {

    }


    @Override
    public int compareTo(Person o) {
        return birthday.compareTo(o.birthday);
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthday=" + birthday +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                ", location=" + location +
                '}';
    }

    private ZonedDateTime birthday;
    private Double height;
    private String passportID;
    private Location location;
    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}