package es.p50.sensorsGenerator.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import es.p50.sensorsGenerator.humidity.HumidityGenerator;
import es.p50.sensorsGenerator.temperature.TemperatureGenerator;

@Component
public class ActionsConsumer {

    @Autowired
    TemperatureGenerator temperatureGenerator;

    @Autowired
    HumidityGenerator humidityGenerator;

    @KafkaListener(topics = "p50-sensors-actions", groupId = "group_actionsConsumers")
    public void consume(String actionString) {
        String[] actionArray = actionString.split("-");  
        Action action = new Action(actionArray[0], actionArray[1], actionArray[2], actionArray[3]);
        if (action.getSensorType().equals("Temperature")) {
            if (action.getAction().equals("airConditioningOn")) {
                if (action.getSensorId()==0) {
                    System.out.println("Air conditioning ON for Temperature Sensor 0");
                    temperatureGenerator.setAction1(true);
                    temperatureGenerator.setRandom1(2);  // decrease Temperature
                }
                if (action.getSensorId()==1) {
                    System.out.println("Air conditioning ON for Temperature Sensor 1");
                    temperatureGenerator.setAction2(true);
                    temperatureGenerator.setRandom2(2);  // decrease Temperature
                }
            }
            else {
                if (action.getSensorId()==0) {
                    System.out.println("Air conditioning OFF for Temperature Sensor 0");
                    temperatureGenerator.setAction1(true);
                    temperatureGenerator.setRandom1(1);  // increase Temperature
                }
                if (action.getSensorId()==1) {
                    System.out.println("Air conditioning OFF for Temperature Sensor 1");
                    temperatureGenerator.setAction2(true);
                    temperatureGenerator.setRandom2(1);  // increase Temperature
                }
            }
        }
        else if (action.getSensorType().equals("Humidity")) {
            if (action.getAction().equals("wateringOn")) {
                if (action.getSensorId()==2) {
                    System.out.println("Watering ON for Humidity Sensor 2");
                    humidityGenerator.setAction1(true);
                    humidityGenerator.setRandom1(1);  // increase Humidity
                }
                if (action.getSensorId()==3) {
                    System.out.println("Watering ON for Humidity Sensor 3");
                    humidityGenerator.setAction2(true);
                    humidityGenerator.setRandom2(1);  // increase Humidity
                }
                if (action.getSensorId()==4) {
                    System.out.println("Watering ON for Humidity Sensor 4");
                    humidityGenerator.setAction3(true);
                    humidityGenerator.setRandom3(1);  // increase Humidity
                }
            }
            else {
                if (action.getSensorId()==2) {
                    System.out.println("Watering OFF for Humidity Sensor 2");
                    humidityGenerator.setAction1(true);
                    humidityGenerator.setRandom1(2);  // increase Humidity
                }
                if (action.getSensorId()==3) {
                    System.out.println("Watering OFF for Humidity Sensor 3");
                    humidityGenerator.setAction2(true);
                    humidityGenerator.setRandom2(2);  // increase Humidity
                }
                if (action.getSensorId()==4) {
                    System.out.println("Watering OFF for Humidity Sensor 4");
                    humidityGenerator.setAction3(true);
                    humidityGenerator.setRandom3(2);  // increase Humidity
                }
            }
        }
    } 

}
