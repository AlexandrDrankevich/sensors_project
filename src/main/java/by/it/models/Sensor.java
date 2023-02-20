package by.it.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Sensor")
public class Sensor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 30, message = "Название должно быть от 3 до 30 символов длиной")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return getId() == sensor.getId() && Objects.equals(getName(), sensor.getName()) && Objects.equals(getMeasurements(), sensor.getMeasurements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMeasurements());
    }


}
