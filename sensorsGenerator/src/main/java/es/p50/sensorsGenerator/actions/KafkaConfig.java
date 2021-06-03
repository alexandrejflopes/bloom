package es.p50.sensorsGenerator.actions;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import java.util.HashMap;
import java.util.Map;

import static es.p50.sensorsGenerator.utils.Utils.IP;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> map = new HashMap<>();
  
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IP+":9092");
        map.put(ConsumerConfig.GROUP_ID_CONFIG,"group_actionsConsumers");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(map);
    }
  
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListner() {
        ConcurrentKafkaListenerContainerFactory<String, String>
        obj = new ConcurrentKafkaListenerContainerFactory<>();
        obj.setConsumerFactory(consumerFactory());
        return obj;
    }
 
}
