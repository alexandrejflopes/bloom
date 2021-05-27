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

    // http://localhost:50080/sensor/0/readings/latest   
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/latest")
    public Temperature latestSensorReading(@PathVariable("id") int id) {
        return service.getLatestTemperature(id);
    }

    // http://localhost:50080/sensor/0/readings/all 
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/all")
    public List<Temperature> allSensorReadings(@PathVariable("id") int id) {
        return service.getAllTemperatures(id);
    }

}
