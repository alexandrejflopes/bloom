package ua.p50.manageApp.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ua.p50.manageApp.models.Sensor;

import static ua.p50.manageApp.utils.Utils.IP;

@Component
public class ExternalApi{

    private RestTemplate template = new RestTemplate();

    public Sensor getCurrentSensorInfo(int sensorId) {

        Sensor sensor = null;
        try {
            String uRL = "http://"+IP+":50080/sensor/" + sensorId + "/readings/latest"; 
            sensor = template.getForObject(uRL, Sensor.class);
        }
        catch (Exception e) {}
        return sensor;
        
    }

    public List<Sensor> getAllSensorInfo(int sensorId) {

        List<Sensor> sensors = null;
        try {
            String uRL = "http://"+IP+":50080/sensor/" + sensorId + "/readings/all";
            Sensor[] sensorsArray = template.getForObject(uRL, Sensor[].class);
            sensors = Arrays.asList(sensorsArray);
        }
        catch (Exception e) {}        
        return sensors;
        
    }
    
    public List<Sensor> getAllLatestSensorsInfo() {

        List<Sensor> sensors = null;
        try {
            String uRL = "http://"+IP+":50080/sensor/all/latest-readings";
            Sensor[] sensorsArray = template.getForObject(uRL, Sensor[].class);
            sensors = Arrays.asList(sensorsArray);
        }
        catch (Exception e) {}        
        return sensors;
        
    }

    public List<Sensor> getAllLatestSensorsInfoPerType(String type) {

        List<Sensor> sensors = null;
        try {
            String uRL = "http://"+IP+":50080/sensor/" + type + "/latest-readings";
            Sensor[] sensorsArray = template.getForObject(uRL, Sensor[].class);
            sensors = Arrays.asList(sensorsArray);
        }
        catch (Exception e) {}        
        return sensors;
        
    }

}
