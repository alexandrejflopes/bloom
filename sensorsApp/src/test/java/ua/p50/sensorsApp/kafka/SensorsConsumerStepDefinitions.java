package ua.p50.sensorsApp.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ua.p50.sensorsApp.models.Sensor;

public class SensorsConsumerStepDefinitions extends SpringIntegrationTest {

  /** 
   
    @Before
    public void setUp() {
      influxDB = InfluxDBFactory.connect("http://localhost:8086", "user", "~");
      resultMapper = new InfluxDBResultMapper();
    }
  

    @When("Exists message in topic {string}")
    public void whenExistsMessageInTopic(String topic) 
      throws Exception {
        if (topic.equals("temperature")) {
            String message = "15-Double-Temperature-Celsius-C-3.0-0";
            kafkaTemplate.send("esp50-sensors-temperature", message);
        }
        else if (topic.equals("humidity")) {
            String message = "15-Double-Humidity-Percentage-%-3.0-0";
            kafkaTemplate.send("esp50-sensors-humidity", message);
        }
        else {
            String message = "15-Double-Co2-Parts per million-ppm-3.0-0";
            kafkaTemplate.send("esp50-sensors-co2", message);
        }
        
    }

    @Then("Message is received by consumer")
    public void thenMessageReceivedByConsumer() 
      throws Exception {

        Thread.sleep(5000);

    }

    @And("Is stored in database")
    public void AndStoredInDatabase() {
        
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=15 AND value=3.0", "esp50sensors"));
        List<Sensor> sensors = resultMapper.toPOJO(queryResult, Sensor.class);
  
        Sensor storedSensor = sensors.get(0);
  
        assertThat(storedSensor.getId()).isEqualTo(15);
        assertThat(storedSensor.getValue()).isEqualTo(3.0);
    }
  
    **/
}
