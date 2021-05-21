package es.p50.sensorsGenerator.humidity;

import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Service
public class HumidityProducer {
   
    private static final String TOPIC = "sensors-humidity";
  
    public ProducerFactory<String, Humidity> producerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }

    private KafkaTemplate<String, Humidity> kafkaTemplate = new KafkaTemplate<String, Humidity>(producerFactory());
    
    
    public void sendHumidity(Humidity newHumidity) {
        this.kafkaTemplate.send(TOPIC, newHumidity);
    }

}
