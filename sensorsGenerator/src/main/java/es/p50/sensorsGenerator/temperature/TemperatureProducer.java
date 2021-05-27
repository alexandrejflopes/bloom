package es.p50.sensorsGenerator.temperature;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Service
@Configuration
public class TemperatureProducer {
   
    private static final String TOPIC = "esp50-sensors-temperature";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendTemperature(Temperature newTemperature) {
        this.kafkaTemplate.send(TOPIC, newTemperature.toString());
    }

}
