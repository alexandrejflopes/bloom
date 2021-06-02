package ua.p50.sensorsApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;
import ua.p50.sensorsApp.models.Sensor;

import static ua.p50.sensorsApp.utils.Utils.IP;

@Service
public class SensorService {

    InfluxDB influxDB = InfluxDBFactory.connect("http://"+IP+":8086", "user", "~");

    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    public void addSensor(Sensor sensor) {
        
        influxDB.createDatabase("esp50sensors");
        influxDB.createRetentionPolicy("defaultPolicy", "esp50sensors", "1d", 1, true);

        Point point = Point.measurement("sensor")
        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .addField("id", sensor.getId())
        .addField("dataType", sensor.getDataType())
        .addField("sensorType", sensor.getSensorType())
        .addField("unit", sensor.getUnit())
        .addField("unitAbreviation", sensor.getUnitAbreviation())
        .addField("value", sensor.getValue())
        .addField("timestamp", sensor.getTimestamp())
        .build();
        influxDB.write("esp50sensors", "defaultPolicy", point);

    }

    public List<Sensor> getAllSensor(int sensorId) {
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=" + sensorId + " ORDER BY time DESC", "esp50sensors"));
        List<Sensor> sensors = resultMapper.toPOJO(queryResult, Sensor.class);
        return sensors;
    }

    public Sensor getLatestSensor(int sensorId) {
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=" + sensorId + " ORDER BY time DESC LIMIT 1", "esp50sensors"));
        List<Sensor> sensors = resultMapper.toPOJO(queryResult, Sensor.class);
        System.out.println(queryResult);
        return sensors.get(0);
    }

    public List<Sensor> getAllLatestSensors() {
        List<Sensor> all = new ArrayList<Sensor>(); 
        for(int i=0;i<7;i++) {
            QueryResult queryResult = influxDB.query(new Query("SELECT * FROM sensor WHERE id=" + i + " ORDER BY time DESC LIMIT 1", "esp50sensors"));
            List<Sensor> sensor = resultMapper.toPOJO(queryResult, Sensor.class);
            all.add(sensor.get(0));
        }
        return all;
    }

}
