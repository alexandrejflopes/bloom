package ua.p50.manageApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import ua.p50.manageApp.models.Alarm;

@Service
public class AlarmService {
    
    @Autowired
    SimpMessagingTemplate template;
	
	public void sendNotification(Alarm a) {
		String message = "Alarm: " + a.getSensorType() + " sensor " + a.getSensorId();
		
		if(a.getAlarm().equals("HIGH")) {
			message = message + " has exceeded the limit ";
		}
		else if(a.getAlarm().equals("LOW")) {
			message = message + " is bellow the limit ";
		}
		else {
			System.out.println("Recieved unkown alarm.");
			return;
		}
		
		message = message + "(" + a.getValue();
		
		if(a.getSensorType().equals("Temperature")) {
			message = message + " C).";
		}
		else if(a.getSensorType().equals("Humidity")) {
			message = message + " %).";
		}
		else {
			System.out.println("Recieved type of sensor.");
			return;
		}
		
		template.convertAndSend("/topic/alarm", message);
	}
	
}
