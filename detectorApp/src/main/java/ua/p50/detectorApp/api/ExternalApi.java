package ua.p50.detectorApp.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ua.p50.detectorApp.models.Temperature;

@Component
public class ExternalApi{

    private RestTemplate template = new RestTemplate();

    public Temperature getCurrentTemperatureInfo(int sensorId) {
        Temperature temperature = null;
        try {
            String uRL = "http://localhost:50080/sensor/" + sensorId + "/readings/latest"; // somewhere in Switzerland...
            temperature = template.getForObject(uRL, Temperature.class);
        }
        catch (Exception e) {}
        return temperature;
    }

}