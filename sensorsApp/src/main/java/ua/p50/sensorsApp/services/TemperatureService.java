package ua.p50.sensorsApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import ua.p50.sensorsApp.models.Temperature;

@Service
public class TemperatureService {

    private List<Temperature> temperaturesSensor0 = new ArrayList<Temperature>();  // only for tests

    public void addTemperatureSensor0(Temperature temperature) {
        temperaturesSensor0.add(temperature);
    }

    public List<Temperature> getAllTemperaturesSensor0() {
        return temperaturesSensor0;
    }

    public void getLatestTemperatureSensor0() {
        // TO DO 
    }
}
