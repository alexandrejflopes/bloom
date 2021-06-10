package ua.p50.detectorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.p50.detectorApp.service.DetectorService;


@CrossOrigin 
@RestController
public class DetectorController {

    @Autowired
    DetectorService service;

    // http://localhost:50050/sensor-limits/temperature?min=30&max=40
    @GetMapping("/sensor-limits/temperature")
    @ResponseBody
    public String newLimitTemperature(@RequestParam String min, @RequestParam String max) { 
        service.setTemperatureMax(Double.parseDouble(max));
        service.setTemperatureMin(Double.parseDouble(min));
        System.out.println("New temperature limits -> min: "+min+" max: "+max);
        return "New limits for temperature defined";
    }  
    
    // http://localhost:50050/sensor-limits/humidity?min=30&max=40
    @GetMapping(value="/sensor-limits/humidity") 
    @ResponseBody
    public String newLimitHumidity(@RequestParam String min, @RequestParam String max) { 
        service.setHumidityMax(Double.parseDouble(max));
        service.setHumidityMin(Double.parseDouble(min));
        System.out.println("New humidity limits -> min: "+min+" max: "+max);
        return "New limits for humidity defined";
    }  

}
