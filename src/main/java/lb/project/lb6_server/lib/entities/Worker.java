package lb.project.lb6_server.lib.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
public class Worker implements Comparable<Worker>, Serializable {

    public Worker() {

    }

    public Worker(String name, Coordinates coordinates, Long salary, Position position, Person person) {

        this.name = Objects.requireNonNull(name, "name must not be null");
        this.coordinates = Objects.requireNonNull(coordinates, "coordinates must not be null");
        this.salary = Objects.requireNonNull(salary, "salary must not be null");
        this.position = Objects.requireNonNull(position, "position must not be null");
        this.person = Objects.requireNonNull(person, "person must not be null");

        if (salary.compareTo(0L) < 0)
            throw new IllegalArgumentException("salary must be higher than 0");
        creationDate = LocalDateTime.now();

    }
    public Worker(String name, Coordinates coordinates, Long salary, Position position, Person person, Status status) {
        this(name, coordinates, salary, position, person);
        this.status = status;
    }

    public Worker(String name, Coordinates coordinates, Long salary, Position position, Person person, Status status, int id, LocalDateTime creationDate) {
        this(name, coordinates, salary, position, person, status);
        this.id = id;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    public Person getPerson() {
        return person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToOne
    @JoinColumn(name = "coordinates_id", nullable = false)
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Long salary;
    private Position position;
    private Status status;
    private Person person;

    public Integer getHashtableKey() {
        return hashtableKey;
    }

    public void setHashtableKey(Integer hashtableKey) {
        this.hashtableKey = hashtableKey;
    }

    private Integer hashtableKey;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    private User owner;

    @Override
    public int compareTo(Worker o) {
        return (int) (salary - o.salary);
    }

    public void copyData(Worker worker) {
        this.name = worker.name;
        this.status = worker.status;
        this.coordinates = worker.coordinates;
        this.person = worker.person;
        this.salary = worker.salary;
        this.position = worker.position;
    }


    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", position=" + (position == null ? "" : position) +
                ", status=" + status +
                ", person=" + person +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}