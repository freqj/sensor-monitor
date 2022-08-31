package dev.alexa.sensor.repository;

import dev.alexa.sensor.domain.Unit;
import org.springframework.data.repository.CrudRepository;

public interface UnitRepo extends CrudRepository <Unit, Long> {
}
