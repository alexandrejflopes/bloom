package ua.p50.manageApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AlarmController {

	
	@MessageMapping("/sendMessage")
    @SendTo("/topic/alarm")
    public ResponseEntity<String> broadcastGroupMessage(@Payload String message) {
        //Sending this message to all the subscribers
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
	
}
