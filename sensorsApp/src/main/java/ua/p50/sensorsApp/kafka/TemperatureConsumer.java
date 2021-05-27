package ua.p50.sensorsApp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ua.p50.sensorsApp.models.Temperature;
import ua.p50.sensorsApp.services.TemperatureService;

@Component
public class TemperatureConsumer {

    @Autowired
    private TemperatureService service;

    @KafkaListener(topics = "esp50-sensors-temperature", groupId = "group_sensorsConsumers")
    public void consume(String temperatureString) { 
        String[] temperatureArray = temperatureString.split("-"); 
        service.addTemperature(new Temperature(Integer.parseInt(temperatureArray[0]), temperatureArray[1], temperatureArray[2], temperatureArray[3], temperatureArray[4], Double.parseDouble(temperatureArray[5]), Integer.parseInt(temperatureArray[6])));
        System.out.println("Saved new temperature for Sensor "+temperatureArray[0]);
    }
}
