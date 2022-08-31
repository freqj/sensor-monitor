package dev.alexa.sensor.controller;

import dev.alexa.sensor.domain.Sensor;
import dev.alexa.sensor.payload.SensorDto;
import dev.alexa.sensor.service.SensorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Api(value = "Sensor REST API")
@RestController
@RequestMapping("/api/v1/sensor")
public class SensorController {

    @Autowired
    private SensorService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('VIEWER')")
    public List<SensorDto> getAllSensors()
    {
        return service.getAllSensors();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SensorDto> saveSensor(@RequestBody @Validated SensorDto sensorDto)
    {
        SensorDto savedSensor = service.saveSensor(sensorDto);
        return new ResponseEntity(savedSensor, HttpStatus.OK);
    }


    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SensorDto> updateSensor(@RequestBody @Validated SensorDto sensorDto)
    {
        SensorDto savedSensor = service.saveSensor(sensorDto);
        return new ResponseEntity(savedSensor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteSensor(@PathVariable(name = "id") Long id)
    {
        service.deleteSensorById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
