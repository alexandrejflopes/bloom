package ua.p50.sensorsApp.kafka;

import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.spring.CucumberContextConfiguration;
import ua.p50.sensorsApp.SensorsAppApplication;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@SpringBootTest(classes = SensorsAppApplication.class, webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public abstract class SpringIntegrationTest {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
  
    InfluxDB influxDB;
  
    InfluxDBResultMapper resultMapper;
}