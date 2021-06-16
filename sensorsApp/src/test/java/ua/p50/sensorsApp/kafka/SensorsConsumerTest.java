package ua.p50.sensorsApp.kafka;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import ua.p50.sensorsApp.models.Sensor;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class SensorsConsumerTest {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private InfluxDB influxDB;

  private InfluxDBResultMapper resultMapper;
 
  @BeforeEach
  public void setUp() {
    influxDB = InfluxDBFactory.connect("http://192.168.160.18:8086", "user", "~");
    resultMapper = new InfluxDBResultMapper();
    //Mockito.doNothing().when(service).addSensor(sensor);
  }

  /**
  @Test
  public void givenEmbeddedKafkaBroker_whenExistsTemperatureMessageInTopic_thenMessageReceivedByConsumerAndServiceInvoked() 
    throws Exception {

      String message = "0-Double-Temperature-Celsius-C-20.0-0";

      kafkaTemplate.send("esp50-sensors-temperature", message);

      consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
      
      Mockito.verify(service, times(1)).addSensor(sensor);
  }
  **/

  @Test
  public void givenEmbeddedKafkaBroker_whenExistsTemperatureMessageInTopic_thenMessageReceivedByConsumer_AndStoredInDatabase() 
    throws Exception {

      String message = "10-Double-Temperature-Celsius-C-3000.0-0";

      kafkaTemplate.send("esp50-sensors-temperature", message);

      Thread.sleep(10000);

      QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=10 AND value=3000.0", "esp50sensors"));
      List<Sensor> sensors = resultMapper.toPOJO(queryResult, Sensor.class);

      Sensor storedSensor = sensors.get(0);

      assertThat(storedSensor.getId()).isEqualTo(10);
      assertThat(storedSensor.getValue()).isEqualTo(3000.0);
  }

  @Test
  public void givenEmbeddedKafkaBroker_whenExistsHumidityMessageInTopic_thenMessageReceivedByConsumer_AndStoredInDatabase() 
    throws Exception {

      String message = "11-Double-Humidity-Percentage-%-3000.0-0";

      kafkaTemplate.send("esp50-sensors-humidity", message);

      Thread.sleep(10000);

      QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=11 AND value=3000.0", "esp50sensors"));
      List<Sensor> sensors = resultMapper.toPOJO(queryResult, Sensor.class);

      Sensor storedSensor = sensors.get(0);

      assertThat(storedSensor.getId()).isEqualTo(11);
      assertThat(storedSensor.getValue()).isEqualTo(3000.0);
  }

  @Test
  public void givenEmbeddedKafkaBroker_whenExistsCo2MessageInTopic_thenMessageReceivedByConsumer_AndStoredInDatabase() 
    throws Exception {

      String message = "12-Double-Co2-Parts per million-ppm-3000.0-0";

      kafkaTemplate.send("esp50-sensors-co2", message);

      Thread.sleep(10000);

      QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=12 AND value=3000.0", "esp50sensors"));
      List<Sensor> sensors = resultMapper.toPOJO(queryResult, Sensor.class);

      Sensor storedSensor = sensors.get(0);

      assertThat(storedSensor.getId()).isEqualTo(12);
      assertThat(storedSensor.getValue()).isEqualTo(3000.0);
  }
}
