package es.p50.sensorsGenerator.humidity;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class HumidityProducer {
   
    private static final String TOPIC = "p50-sensors-humidity";
  
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendHumidity(Humidity newHumidity) {
        this.kafkaTemplate.send(TOPIC, newHumidity.toString());
    }

}
