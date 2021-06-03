package ua.p50.sensorsApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ua.p50.sensorsApp.models.Sensor;
import ua.p50.sensorsApp.service.SensorService;

@CrossOrigin 
@RestController
public class SensorController {

    @Autowired
    private SensorService service;

    // http://localhost:50080/sensor/0/readings/latest   
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/latest")
    public Sensor latestSensorReading(@PathVariable("id") int id) {
        return service.getLatestSensor(id);
    }

    // http://localhost:50080/sensor/0/readings/all 
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/all")
    public List<Sensor> allSensorReadings(@PathVariable("id") int id) {
        return service.getAllSensor(id);
    }
    
    // http://localhost:50080/sensor/all/latest-readings
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/all/latest-readings")
    public List<Sensor> allLatestSensorsReadings() {
        return service.getAllLatestSensors();
    }

    // http://localhost:50080/sensor/temperature/latest-readings
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{type}/latest-readings")
    public List<Sensor> allLatestSensorsReadingsPerType(@PathVariable("type") String type) {
        return service.getAllLatestSensorsPerType(type);
    }
    
    
}
