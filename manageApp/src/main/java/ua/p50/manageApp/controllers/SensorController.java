package ua.p50.manageApp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.p50.manageApp.models.Sensor;
import ua.p50.manageApp.services.SensorService;

@CrossOrigin 
@RestController
public class SensorController {

    @Autowired
    private SensorService service;

    // http://localhost:50060/sensor/0/readings/latest   
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/latest")
    public Sensor latestSensorReading(@PathVariable("id") int id) {
        return service.getLatestSensor(id);
    }

    // http://localhost:50060/sensor/0/readings/all/10 
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}/readings/all/{count}")
    public List<Sensor> allSensorReadings(@PathVariable("id") int id, @PathVariable("count") int count) {
        return new ArrayList<Sensor>(service.getAllSensor(id).subList(0, count));
    }

    // http://localhost:50060/sensor/all/latest-readings
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/all/latest-readings")
    public List<Sensor> allLatestSensorsReadings() {
        return service.getAllLatestSensors();
    }

    // http://localhost:50060/sensor/temperature/latest-readings
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{type}/latest-readings")
    public List<Sensor> allLatestSensorsReadingsPerType(@PathVariable("type") String type) {
        return service.getAllLatestSensorsPerType(type);
    }
    
    // http://localhost:50060/sensor/0 
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor/{id}")
    public Map<String, String> infoSensor(@PathVariable("id") int id) {
        HashMap<String, String> info = new HashMap<>();
        info.put("sensorId", Integer.toString(id));
        info.put("dataType", "Double");
        if(id==0||id==1) info.put("sensorType", "Temperature");
        else if(id==2||id==3||id==4) info.put("sensorType", "Humidity");
        else info.put("sensorType", "Co2");
        return info;
    }

    // http://localhost:50060/sensor-limits/temperature/new?min=30&max=40
    @GetMapping("/sensor-limits/temperature/new") // com o Post não estava a dar, pelo menos no browser
    @ResponseBody
    public String newLimitTemperature(@RequestParam String min, @RequestParam String max) { 
        service.newLimits("temperature", min, max);
        return "New limits for temperature defined";
    }  
    
    // http://localhost:50060/sensor-limits/humidity/new?min=30&max=40
    @GetMapping(value="/sensor-limits/humidity/new") // com o Post não estava a dar
    @ResponseBody
    public String newLimitHumidity(@RequestParam String min, @RequestParam String max) { 
        service.newLimits("humidity", min, max);
        return "New limits for humidity defined";
    }  

    // http://localhost:50060/sensor-limits/temperature
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor-limits/temperature")
    public Map<String,String> limitTemperature() {
        return service.getLimits("temperature");
    }

    // http://localhost:50060/sensor-limits/humidity
    @CrossOrigin(origins="*")
    @GetMapping(value = "/sensor-limits/humidity")
    public Map<String,String> limitHumidity() {
        return service.getLimits("humidity");
    }

}
