package by.it.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 30, message = "Название должно быть от 3 до 30 символов длиной")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
