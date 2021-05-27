package es.p50.sensorsGenerator.co2;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CO2Producer {
   
    private static final String TOPIC = "esp50-sensors-co2";
  
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendCO2(CO2 newCO2) {
        this.kafkaTemplate.send(TOPIC, newCO2.toString());
    }

}
