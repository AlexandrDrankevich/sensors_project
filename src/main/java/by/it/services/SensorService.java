package by.it.services;

import by.it.models.Sensor;
import by.it.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void create(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> checkNameUnique(Sensor sensor) {
        return sensorRepository.findByName(sensor.getName());
    }


}
