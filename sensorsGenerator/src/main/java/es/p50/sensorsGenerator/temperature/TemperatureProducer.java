package es.p50.sensorsGenerator.temperature;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemperatureProducer {
   
    private static final String TOPIC = "sensors-temperature";

    public ProducerFactory<String, Temperature> producerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }

    private KafkaTemplate<String, Temperature> kafkaTemplate = new KafkaTemplate<String, Temperature>(producerFactory());
    
    public void sendTemperature(Temperature newTemperature) {
        this.kafkaTemplate.send(TOPIC, newTemperature);
    }

}
