package ua.p50.detectorApp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ua.p50.detectorApp.models.Alarm;


@Service
public class AlarmsProducer {
    
    private static final String TOPIC = "esp50-alarms";
  
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendAlarm(Alarm newAlarm) {
        this.kafkaTemplate.send(TOPIC, newAlarm.toString());
    }

}
