package ua.p50.sensorsApp.services;

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
import ua.p50.sensorsApp.models.Temperature;

@Service
public class TemperatureService {

    InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "user", "~");

    public void addTemperature(Temperature temperature) {
        //influxDB.createDatabase("esp50sensors");
        //influxDB.createRetentionPolicy("defaultPolicy", "esp50sensors", "30d", 1, true);
        Point point = Point.measurement("temperature")
        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .addField("id", temperature.getId())
        .addField("dataType", temperature.getDataType())
        .addField("sensorType", temperature.getSensorType())
        .addField("unit", temperature.getUnit())
        .addField("unitAbreviation", temperature.getUnitAbreviation())
        .addField("value", temperature.getValue())
        .addField("timestamp", temperature.getTimestamp())
        .build();
        influxDB.write("esp50sensors", "defaultPolicy", point);

    }

    public List<Temperature> getAllTemperatures(int sensorId) {
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM temperature WHERE id=" + sensorId + " ORDER BY time DESC", "esp50sensors"));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Temperature> temperatures = resultMapper.toPOJO(queryResult, Temperature.class);
        return temperatures;
    }

    public Temperature getLatestTemperature(int sensorId) {
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM temperature WHERE id=" + sensorId + " ORDER BY time DESC LIMIT 1", "esp50sensors"));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Temperature> temperatures = resultMapper.toPOJO(queryResult, Temperature.class);
        return temperatures.get(0);
    }
}
