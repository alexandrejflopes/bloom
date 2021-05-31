package ua.p50.manageApp.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ua.p50.manageApp.models.Temperature;

@Component
public class ExternalApi{

    private RestTemplate template = new RestTemplate();

    public Temperature getCurrentTemperatureInfo(int sensorId) {

        Temperature temperature = null;
        try {
            //String uRL = "http://localhost:50080/sensor/" + sensorId + "/readings/latest";
            String uRL = "http://192.168.160.87:50080/sensor/" + sensorId + "/readings/latest"; 
            temperature = template.getForObject(uRL, Temperature.class);
        }
        catch (Exception e) {}
        return temperature;
        
    }

    public List<Temperature> getAllTemperaturesInfo(int sensorId) {

        List<Temperature> temperatures = null;
        try {
            //String uRL = "http://localhost:50080/sensor/" + sensorId + "/readings/all";
            String uRL = "http://192.168.160.87:50080/sensor/" + sensorId + "/readings/all";
            Temperature[] temperaturesArray = template.getForObject(uRL, Temperature[].class);
            temperatures = Arrays.asList(temperaturesArray);
        }
        catch (Exception e) {}        
        return temperatures;
        
    }

}
