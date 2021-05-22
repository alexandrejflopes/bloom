package ua.p50.sensorsApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ua.p50.sensorsApp.models.Temperature;
import ua.p50.sensorsApp.services.TemperatureService;

@CrossOrigin 
@RestController
public class TemperatureController {

    @Autowired
    private TemperatureService service;

    // http://localhost:50080/sensor/0/readings/latest     HARDCODED FOR NOW
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/latest")
    public Temperature latestSensorReading(@PathVariable("id") int id) {
        return new Temperature(0, "Double", "Temperature", "Celsius", "C", 25.0, 0);
    }

    // http://localhost:50080/sensor/0/readings/all   FROM KAFKA FOR NOW
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/all")
    public List<Temperature> allSensorReadings(@PathVariable("id") int id) {
        return service.getAllTemperaturesSensor0();
    }

}
