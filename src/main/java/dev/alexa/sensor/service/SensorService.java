package dev.alexa.sensor.service;

import dev.alexa.sensor.domain.Sensor;
import dev.alexa.sensor.payload.SensorDto;

import java.util.List;

public interface SensorService {

    List<SensorDto> getAllSensors();
    List<SensorDto> getSensorsByText(String text);

    SensorDto getSensorById(Long id);
    SensorDto updateSensorById(Long id);
    SensorDto saveSensor(SensorDto sensorDto);
    void deleteSensorById(Long id);
}
