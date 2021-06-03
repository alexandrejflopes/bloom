package ua.p50.sensorsApp.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ua.p50.sensorsApp.models.Sensor;
import ua.p50.sensorsApp.services.SensorService;

@Component
public class SensorConsumers {
  
    @Autowired
    private SensorService service;

    private CountDownLatch latch = new CountDownLatch(2);

    public CountDownLatch getLatch() {
      return latch;
    }


    @KafkaListener(topics = "esp50-sensors-temperature")
    public void consumeTemperature(String temperatureString) { 
        String[] temperatureArray = temperatureString.split("-"); 
        service.addSensor(new Sensor(Integer.parseInt(temperatureArray[0]), temperatureArray[1], temperatureArray[2], temperatureArray[3], temperatureArray[4], Double.parseDouble(temperatureArray[5]), Integer.parseInt(temperatureArray[6])));
        System.out.println("Saved new temperature for Sensor "+temperatureArray[0]);
        latch.countDown();
    }

    @KafkaListener(topics = "esp50-sensors-humidity")
    public void consumeHumidity(String humidityString) { 
        String[] humidityArray = humidityString.split("-"); 
        service.addSensor(new Sensor(Integer.parseInt(humidityArray[0]), humidityArray[1], humidityArray[2], humidityArray[3], humidityArray[4], Double.parseDouble(humidityArray[5]), Long.parseLong(humidityArray[6])));
        System.out.println("Saved new humidity for Sensor "+humidityArray[0]);
    }

    @KafkaListener(topics = "esp50-sensors-co2", groupId = "group_sensorsConsumers")
    public void consume(String co2String) { 
        String[] co2Array = co2String.split("-"); 
        service.addSensor(new Sensor(Integer.parseInt(co2Array[0]), co2Array[1], co2Array[2], co2Array[3], co2Array[4], Double.parseDouble(co2Array[5]), Long.parseLong(co2Array[6])));
        System.out.println("Saved new co2 for Sensor "+co2Array[0]);
    }
}
