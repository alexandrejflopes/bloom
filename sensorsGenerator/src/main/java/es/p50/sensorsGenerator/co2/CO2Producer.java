package es.p50.sensorsGenerator.co2;

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
public class CO2Producer {
   
    private static final String TOPIC = "sensors-co2";
  
    public ProducerFactory<String, CO2> producerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }

    private KafkaTemplate<String, CO2> kafkaTemplate = new KafkaTemplate<String, CO2>(producerFactory());
    
    
    public void sendCO2(CO2 newCO2) {
        this.kafkaTemplate.send(TOPIC, newCO2);
    }

}
