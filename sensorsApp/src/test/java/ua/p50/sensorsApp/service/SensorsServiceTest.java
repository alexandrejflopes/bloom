package ua.p50.sensorsApp.service;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.p50.sensorsApp.models.Sensor;

import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SensorsServiceTest {

    @Mock( lenient = true)
    private InfluxDBResultMapper mapper;

    @Mock( lenient = true)
    private InfluxDB db;

    @InjectMocks
	private SensorService service;
	
    private Sensor sensor0new;
    private Sensor sensor0;
    private Sensor sensor1;
    private Sensor sensor2;
    private Sensor sensor3;
    private Sensor sensor4;
    private Sensor sensor5;
    private Sensor sensor6;
    
    List<Sensor> sensors = new ArrayList<Sensor>();

    @BeforeEach
    public void setUp() {

        sensor0new = new Sensor(0, "Double", "Temperature", "Celsius", "C", 20.0, 1);
        sensor0 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 21.0, 0);
        sensor1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 30.0, 0);

        sensor2 = new Sensor(0, "Double", "Humidity", "Percentage", "%", 40.0, 0);
        sensor3 = new Sensor(0, "Double", "Humidity", "Percentage", "%", 50.0, 0);
        sensor4 = new Sensor(0, "Double", "Humidity", "Percentage", "%", 60.0, 0);
        
        sensor5 = new Sensor(0, "Double", "Co2", "Parts per million", "ppm", 4400.0, 0);
        sensor6 = new Sensor(0, "Double", "Co2", "Parts per million", "ppm", 5000.0, 0);

        sensors.add(sensor0new);
        sensors.add(sensor0);
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);
        sensors.add(sensor4);
        sensors.add(sensor5);
        sensors.add(sensor6);

        Query query0 = new Query("SELECT * FROM sensor WHERE id=0 ORDER BY time DESC LIMIT 1", "esp50sensors");
        Query query1 = new Query("SELECT * FROM sensor WHERE id=1 ORDER BY time DESC LIMIT 1", "esp50sensors");
        Query query2 = new Query("SELECT * FROM sensor WHERE id=2 ORDER BY time DESC LIMIT 1", "esp50sensors");
        Query query3 = new Query("SELECT * FROM sensor WHERE id=3 ORDER BY time DESC LIMIT 1", "esp50sensors");
        Query query4 = new Query("SELECT * FROM sensor WHERE id=4 ORDER BY time DESC LIMIT 1", "esp50sensors");
        Query query5 = new Query("SELECT * FROM sensor WHERE id=5 ORDER BY time DESC LIMIT 1", "esp50sensors");
        Query query6 = new Query("SELECT * FROM sensor WHERE id=6 ORDER BY time DESC LIMIT 1", "esp50sensors");

        Query query0all = new Query("SELECT * FROM sensor WHERE id=0 ORDER BY time DESC", "esp50sensors");
        
        QueryResult result = new QueryResult();

        Mockito.when(db.query(query0)).thenReturn(result);
        Mockito.when(db.query(query1)).thenReturn(result);
        Mockito.when(db.query(query2)).thenReturn(result);
        Mockito.when(db.query(query3)).thenReturn(result);
        Mockito.when(db.query(query4)).thenReturn(result);
        Mockito.when(db.query(query5)).thenReturn(result);
        Mockito.when(db.query(query6)).thenReturn(result);
        Mockito.when(db.query(query0all)).thenReturn(result);
        Mockito.when(mapper.toPOJO(any(QueryResult.class), Mockito.eq(Sensor.class))).thenReturn(sensors);


    }

    @Test
    public void whenInDatabase_thenAllResultForSensor0ShouldBeFoundAndTheFirstMustHaveBiggerTimestamp() {
        int id = 0;
        List<Sensor> found = service.getAllSensor(id);
        assertThat(found.get(0).getId()).isEqualTo(sensor0new.getId());
        assertThat(found.get(1).getId()).isEqualTo(sensor0.getId());
        assertThat(found.get(0).getTimestamp()).isGreaterThan(found.get(1).getTimestamp());
    }

    @Test
    public void whenInDatabase_thenLatestResultForSensor0ShouldBeFound(){
        int id = 0;
        Sensor found = service.getLatestSensor(id);
        assertThat(found.getId()).isEqualTo(sensor0new.getId());
        assertThat(found.getValue()).isEqualTo(sensor0new.getValue());

    }
    
    @Test
    public void whenInDatabase_thenAllLatestResultsShouldBeFound(){
        List<Sensor> found = service.getAllLatestSensors();
        assertThat(found.get(0).getId()).isEqualTo(sensors.get(0).getId());
        assertThat(found.get(0).getValue()).isEqualTo(sensors.get(0).getValue());

	}
}
