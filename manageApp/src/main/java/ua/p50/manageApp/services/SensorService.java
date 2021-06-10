package ua.p50.manageApp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.p50.manageApp.api.ExternalApi;
import ua.p50.manageApp.models.Sensor;

@Service
public class SensorService {
    
    @Autowired
    private ExternalApi restUtil;

    private HashMap<String, String> limitsTemperature;
    private HashMap<String, String> limitsHumidity;

    SensorService() {
        this.limitsTemperature = new HashMap<>();
        this.limitsHumidity = new HashMap<>();
        this.limitsTemperature.put("min","24");
        this.limitsTemperature.put("max","26");
        this.limitsHumidity.put("min","50");
        this.limitsHumidity.put("max","70");
    }

    public Sensor getLatestSensor(int sensorId) {

        return restUtil.getCurrentSensorInfo(sensorId);
        
    }

    public List<Sensor> getAllSensor(int sensorId) {

        return restUtil.getAllSensorInfo(sensorId);
        
    }

    public List<Sensor> getAllLatestSensors() {
        
        return restUtil.getAllLatestSensorsInfo();

    }

    public List<Sensor> getAllLatestSensorsPerType(String type) {
        
        return restUtil.getAllLatestSensorsInfoPerType(type);
          
    }

    public void newLimits(String type, String min, String max) {
        if (type.equals("temperature")) { limitsTemperature.put("min", min); limitsTemperature.put("max", max); } 
        else { limitsHumidity.put("min", min); limitsHumidity.put("max", max); } 
        restUtil.publishNewLimits(type, min, max);
    }

    public Map<String, String> getLimits(String type) {
        if (type.equals("temperature")) return limitsTemperature;
        else return limitsHumidity;
    }

}
