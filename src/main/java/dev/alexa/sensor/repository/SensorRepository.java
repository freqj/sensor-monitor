package dev.alexa.sensor.repository;

import dev.alexa.sensor.domain.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

    List<Sensor> findAllByTitleContainingIgnoreCaseOrModelContainingIgnoreCase(String title, String model);
}
