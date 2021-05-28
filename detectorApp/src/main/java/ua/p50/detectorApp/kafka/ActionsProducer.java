package ua.p50.detectorApp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ua.p50.detectorApp.models.Action;

@Service
public class ActionsProducer {
    
    private static final String TOPIC = "esp50-sensors-actions";
  
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendAction(Action newAction) {
        this.kafkaTemplate.send(TOPIC, newAction.toString());
    }

}