package dev.alexa.sensor.repository;

import dev.alexa.sensor.domain.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepo extends CrudRepository<Type, Long> {
}
