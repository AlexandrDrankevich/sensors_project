package by.it.util;

import by.it.models.Measurement;
import by.it.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;
        if (measurement.getSensor() == null) return;
        if (!sensorService.findByName(measurement.getSensor()).isPresent()) {
            errors.rejectValue("sensor", "Сенсор с таким названием не зарегистрирован");
        }
    }
}
