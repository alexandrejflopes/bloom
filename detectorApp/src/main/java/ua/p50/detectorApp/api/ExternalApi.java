package ua.p50.detectorApp.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ua.p50.detectorApp.models.Sensor;

import static ua.p50.detectorApp.utils.Utils.IP;

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

}
