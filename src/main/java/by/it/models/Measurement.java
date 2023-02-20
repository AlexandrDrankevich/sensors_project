package by.it.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    @NotNull(message = "Значение не должно быть пустым")
    @Min(value = -100, message = "Значение не должно быть меньше -100")
    @Max(value = 100, message = "Значение не должно быть больше 100")
    Double value;
    @Column(name = "raining")
    @NotNull
    Boolean raining;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;
    @Column(name = "measurement_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date measurement_date;
    public Date getMeasurement_date() {
        return measurement_date;
    }

    public void setMeasurement_date(Date measurement_date) {
        this.measurement_date = measurement_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return getId() == that.getId() && Double.compare(that.getValue(), getValue()) == 0 && isRaining() == that.isRaining() && Objects.equals(getSensor(), that.getSensor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), isRaining(), getSensor());
    }
}
