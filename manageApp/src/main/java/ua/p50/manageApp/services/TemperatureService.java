package ua.p50.manageApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.p50.manageApp.api.ExternalApi;
import ua.p50.manageApp.models.Temperature;

@Service
public class TemperatureService {
    
    @Autowired
    private ExternalApi restUtil;

    public Temperature getLatestTemperature(int sensorId) {

        return restUtil.getCurrentTemperatureInfo(sensorId);
        
    }

    public List<Temperature> getAllTemperatures(int sensorId) {

        return restUtil.getAllTemperaturesInfo(sensorId);
        
    }

}
