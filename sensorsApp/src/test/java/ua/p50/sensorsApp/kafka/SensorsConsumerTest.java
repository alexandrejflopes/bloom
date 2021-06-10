package ua.p50.sensorsApp.kafka;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import ua.p50.sensorsApp.models.Sensor;
import ua.p50.sensorsApp.service.SensorService;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class SensorsConsumerTest {

    @Autowired
    SensorsConsumer consumer;

    @Mock
    private SensorService service;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Test
    public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived() 
      throws Exception {

        String message = "{\"id\":\"0\"dataType\":\"Double\"sensorType\":\"Temperature\"unit\":\"Celsius\"unitAbreviation\":\"C\"value\":\"20.0\"timestamp\":\"0\"}";

        kafkaTemplate.send("esp50-sensors-temperature", message);

        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

        Sensor sensor = new Sensor(0, "Double", "Temperature", "Celsius", "C", 20.0, 0);
        //verify(service, times(1)).addSensor(sensor);
    }
}

