package ua.p50.manageApp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import ua.p50.manageApp.models.Alarm;
import ua.p50.manageApp.services.AlarmService;

@Component
public class AlertConsumer {

    @Autowired
    private AlarmService service;

    @KafkaListener(topics = "esp50-alarms", groupId = "group_alarmsConsumers")
    public void consume(String alarmString) { 
        String[] alarmArray = alarmString.split("-"); 
        Alarm alarm = new Alarm(alarmArray[0], alarmArray[1], alarmArray[2], alarmArray[3], alarmArray[4]);
        service.sendNotification(alarm);
    }
}
