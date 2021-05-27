package es.p50.sensorsGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@SpringBootApplication
@EnableScheduling
public class SensorsGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsGeneratorApplication.class, args);
	}

}
