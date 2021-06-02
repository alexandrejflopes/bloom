package ua.p50.manageApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.p50.manageApp.api.ExternalApi;
import ua.p50.manageApp.models.Sensor;

@Service
public class SensorService {
    
    @Autowired
    private ExternalApi restUtil;

    public Sensor getLatestSensor(int sensorId) {

        return restUtil.getCurrentSensorInfo(sensorId);
        
    }

    public List<Sensor> getAllSensor(int sensorId) {

        return restUtil.getAllSensorInfo(sensorId);
        
    }

    public List<Sensor> getAllLatestSensors() {
        
        return restUtil.getAllLatestSensorsInfo();
        
    }

}
