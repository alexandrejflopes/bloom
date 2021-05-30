package ua.p50.detectorApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DetectorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetectorAppApplication.class, args);
	}

}
