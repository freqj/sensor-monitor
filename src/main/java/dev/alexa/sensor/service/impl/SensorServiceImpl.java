package dev.alexa.sensor.service.impl;

import dev.alexa.sensor.domain.Sensor;
import dev.alexa.sensor.payload.SensorDto;
import dev.alexa.sensor.repository.SensorRepository;
import dev.alexa.sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;


    @Override
    public List<SensorDto> getAllSensors() {
        List<Sensor> sensors = (List<Sensor>) sensorRepository.findAll();
        List<SensorDto> sensorDtos = sensors.stream().map(sensor -> mapToDto(sensor)).collect(Collectors.toList());
        return sensorDtos;
    }

    @Override
    public List<SensorDto> getSensorsByText(String text) {
        List<SensorDto> sensorDtos = sensorRepository.findAllByTitleContainingIgnoreCaseOrModelContainingIgnoreCase(text, text)
                .stream().map(sensor -> mapToDto(sensor)).collect(Collectors.toList());
        return sensorDtos;
    }

    @Override
    public SensorDto getSensorById(Long id) {
        return mapToDto(sensorRepository.findById(id).get());
    }

    @Override
    public SensorDto updateSensorById(Long id) {
        return null;
    }

    @Override
    public SensorDto saveSensor(SensorDto sensorDto) {
        if (sensorDto.getRange().get("from") > sensorDto.getRange().get("to"))
        {
            throw new InvalidParameterException("to value should be higher then to from");
        }
        Sensor savedSensor = sensorRepository.save(mapToSensor(sensorDto));
        return mapToDto(savedSensor);
    }

    @Override
    public void deleteSensorById(Long id) {
        sensorRepository.deleteById(id);
    }


    private SensorDto mapToDto(Sensor sensor) {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setId(sensor.getId());
        sensorDto.setDescription(sensor.getDescription());
        sensorDto.setLocation(sensor.getLocation());
        sensorDto.setModel(sensor.getModel());
        sensorDto.setTitle(sensor.getTitle());
        sensorDto.setType(sensor.getType());
        sensorDto.setUnit(sensor.getUnit());
        HashMap<String, Integer> range = new HashMap<>();
        range.put("to", sensor.getRangeTo());
        range.put("from", sensor.getRangeFrom());
        sensorDto.setRange(range);

        return sensorDto;

    }

    private Sensor mapToSensor(SensorDto sensorDto)
    {
        Sensor sensor = new Sensor();

        sensor.setId(sensorDto.getId());
        sensor.setDescription(sensorDto.getDescription());
        sensor.setLocation(sensorDto.getLocation());
        sensor.setModel(sensorDto.getModel());
        sensor.setTitle(sensorDto.getTitle());
        sensor.setType(sensorDto.getType());
        sensor.setUnit(sensorDto.getUnit());
        sensor.setRangeFrom(sensorDto.getRange().get("from"));
        sensor.setRangeTo(sensorDto.getRange().get("to"));

        return sensor;
    }

}
