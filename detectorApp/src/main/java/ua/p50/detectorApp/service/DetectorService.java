package ua.p50.detectorApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.p50.detectorApp.api.ExternalApi;
import ua.p50.detectorApp.kafka.ActionsProducer;
import ua.p50.detectorApp.kafka.AlarmsProducer;
import ua.p50.detectorApp.models.Action;
import ua.p50.detectorApp.models.Alarm;
import ua.p50.detectorApp.models.Sensor;

@Service
public class DetectorService {

    @Autowired
    private ExternalApi restUtil;

    @Autowired
    private ActionsProducer actionsProducer;

    @Autowired
    private AlarmsProducer alarmsProducer;

    private boolean airConditioningOnSensor0 = false;
    private boolean airConditioningOnSensor1 = false;

    private boolean wateringSensor2 = false;
    private boolean wateringSensor3 = false;
    private boolean wateringSensor4 = false;

    private double temperatureMax = 26.0;
    private double temperatureMin = 24.0;
    private double humidityMax = 90.0;
    private double humidityMin = 50.0;

    @Scheduled(fixedRate = 3000) // every 3 seconds
    public void inspectTemperatureSensor0() {

        Action action = null;
        Alarm alarm = null;
        Sensor currentSensor = restUtil.getCurrentSensorInfo(0);
   
        if (currentSensor.getValue() > temperatureMax) {
            if (!airConditioningOnSensor0) {
                airConditioningOnSensor0 = true;
                action = new Action("0", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOn");
                alarm = new Alarm("0", "Temperature", Double.toString(temperatureMax), "HIGH", Long.toString(System.currentTimeMillis()));
                System.out.println("Air conditioning ON for temperature sensor 0");
            }
        }
        else if (currentSensor.getValue() < temperatureMin) {
            if (airConditioningOnSensor0) {
                airConditioningOnSensor0 = false;
                action = new Action("0", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOff");
                alarm = new Alarm("0", "Temperature", Double.toString(temperatureMin), "LOW", Long.toString(System.currentTimeMillis()));
                System.out.println("Air conditioning OFF for temperature sensor 0");
            }
        }

        if (action!=null) {
            actionsProducer.sendAction(action);
            alarmsProducer.sendAlarm(alarm);
        }
    }
      
    
    @Scheduled(fixedRate = 4000) // every 4 seconds
    public void inspectTemperatureSensor1() {

        Action action = null;
        Alarm alarm = null;
        Sensor currentSensor = restUtil.getCurrentSensorInfo(1);

        if (currentSensor.getValue() > temperatureMax) {
            if (!airConditioningOnSensor1) {
                airConditioningOnSensor1 = true;
                action = new Action("1", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOn");
                alarm = new Alarm("1", "Temperature", Double.toString(temperatureMax), "HIGH", Long.toString(System.currentTimeMillis()));
                System.out.println("Air conditioning ON for temperature sensor 1");
            }
        }
        else if (currentSensor.getValue() < temperatureMin) {
            if (airConditioningOnSensor1) {
                airConditioningOnSensor1 = false;
                action = new Action("1", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOff");
                alarm = new Alarm("1", "Temperature", Double.toString(temperatureMin), "LOW", Long.toString(System.currentTimeMillis()));
                System.out.println("Air conditioning OFF for temperature sensor 1");
            }
        }

        if (action!=null) {
            actionsProducer.sendAction(action);
            alarmsProducer.sendAlarm(alarm);
        }
    }

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void inspectHumiditySensor2() {

        Action action = null;
        Alarm alarm = null;
        Sensor currentSensor = restUtil.getCurrentSensorInfo(2);
        if (currentSensor.getValue() > humidityMax) {
            if (wateringSensor2) {
                wateringSensor2 = false;
                action = new Action("2", "Humidity", Long.toString(System.currentTimeMillis()),"wateringOff");
                alarm = new Alarm("2", "Humidity", Double.toString(humidityMax), "HIGH", Long.toString(System.currentTimeMillis()));
                System.out.println("Watering OFF for humidity sensor 3");
            }
        }
        else if (currentSensor.getValue() < humidityMin) {
            if (!wateringSensor2) {
                wateringSensor2 = true;
                action = new Action("2", "Humidity", Long.toString(System.currentTimeMillis()),"wateringOn");
                alarm = new Alarm("2", "Humidity", Double.toString(humidityMin), "LOW", Long.toString(System.currentTimeMillis()));
                System.out.println("Watering ON for humidity sensor 2");
            }
        }

        if (action!=null) {
            actionsProducer.sendAction(action);
            alarmsProducer.sendAlarm(alarm);
        }
    }

    @Scheduled(fixedRate = 6000) // every 6 seconds
    public void inspectHumiditySensor3() {

        Action action = null;
        Alarm alarm = null;
        Sensor currentSensor = restUtil.getCurrentSensorInfo(3);
        if (currentSensor.getValue() > humidityMax) {
            if (wateringSensor3) {
                wateringSensor3 = false;
                action = new Action("3", "Humidity", Long.toString(System.currentTimeMillis()),"wateringOff");
                alarm = new Alarm("3", "Humidity", Double.toString(humidityMax), "HIGH", Long.toString(System.currentTimeMillis()));
                System.out.println("Watering OFF for humidity sensor 3");
            }
        }
        else if (currentSensor.getValue() < humidityMin) {
            if (!wateringSensor3) {
                wateringSensor3 = true;
                action = new Action("3", "Humidity", Long.toString(System.currentTimeMillis()),"wateringOn");
                alarm = new Alarm("3", "Humidity", Double.toString(humidityMin), "LOW", Long.toString(System.currentTimeMillis()));
                System.out.println("Watering ON for humidity sensor 3");
            }
        }

        if (action!=null) {
            actionsProducer.sendAction(action);
            alarmsProducer.sendAlarm(alarm);
        }
    }

    @Scheduled(fixedRate = 7000) // every 7 seconds
    public void inspectHumiditySensor4() {

        Action action = null;
        Alarm alarm = null;
        Sensor currentSensor = restUtil.getCurrentSensorInfo(4);

        if (currentSensor.getValue() > humidityMax) {
            if (wateringSensor4) {
                wateringSensor4 = false;
                action = new Action("4", "Humidity", Long.toString(System.currentTimeMillis()),"wateringOff");
                alarm = new Alarm("4", "Humidity", Double.toString(humidityMax), "HIGH", Long.toString(System.currentTimeMillis()));
                System.out.println("Watering OFF for humidity sensor 4");
            }
        }
        else if (currentSensor.getValue() < humidityMin) {
            if (!wateringSensor4) {
                wateringSensor4 = true;
                action = new Action("4", "Humidity", Long.toString(System.currentTimeMillis()),"wateringOn");
                alarm = new Alarm("4", "Humidity", Double.toString(humidityMin), "LOW", Long.toString(System.currentTimeMillis()));
                System.out.println("Watering ON for humidity sensor 4");
            }
        }

        if (action!=null) {
            actionsProducer.sendAction(action);
            alarmsProducer.sendAlarm(alarm);
        }
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public void setHumidityMax(double humidityMax) {
        this.humidityMax = humidityMax;
    }

    public void setHumidityMin(double humidityMin) {
        this.humidityMin = humidityMin;
    }
    
}