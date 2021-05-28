package ua.p50.detectorApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.p50.detectorApp.api.ExternalApi;
import ua.p50.detectorApp.kafka.ActionsProducer;
import ua.p50.detectorApp.kafka.AlarmsProducer;
import ua.p50.detectorApp.models.Action;
import ua.p50.detectorApp.models.Alarm;
import ua.p50.detectorApp.models.Temperature;

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

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void inspectTemperatureSensor0() {

        try {

            Action action = null;
            Alarm alarm = null;
            Temperature currentTemperature = restUtil.getCurrentTemperatureInfo(0);
            
            if (currentTemperature.getValue() > 26) {
                if (!airConditioningOnSensor0) {
                    airConditioningOnSensor0 = true;
                    action = new Action("0", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOn");
                    alarm = new Alarm("0", "Temperature", "26", "HIGH", Long.toString(System.currentTimeMillis()));
                    System.out.println("Air conditioning ON for temperature sensor 0");
                }
            }
            else if (currentTemperature.getValue() < 24) {
                if (airConditioningOnSensor0) {
                    airConditioningOnSensor0 = false;
                    action = new Action("0", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOff");
                    alarm = new Alarm("0", "Temperature", "24", "LOW", Long.toString(System.currentTimeMillis()));
                    System.out.println("Air conditioning OFF for temperature sensor 0");
                }
            }

            if (action!=null) {
                actionsProducer.sendAction(action);
                alarmsProducer.sendAlarm(alarm);
            }
        }
        catch (Exception e) {
        }

    }

    @Scheduled(fixedRate = 7000) // every 7 seconds
    public void inspectTemperatureSensor1() {

        try {

            Action action = null;
            Alarm alarm = null;
            Temperature currentTemperature = restUtil.getCurrentTemperatureInfo(1);

            if (currentTemperature.getValue() > 26) {
                if (!airConditioningOnSensor1) {
                    airConditioningOnSensor1 = true;
                    action = new Action("1", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOn");
                    alarm = new Alarm("1", "Temperature", "26", "HIGH", Long.toString(System.currentTimeMillis()));
                    System.out.println("Air conditioning ON for temperature sensor 1");
                }
            }
            else if (currentTemperature.getValue() < 24) {
                if (airConditioningOnSensor1) {
                    airConditioningOnSensor1 = false;
                    action = new Action("1", "Temperature", Long.toString(System.currentTimeMillis()),"airConditioningOff");
                    alarm = new Alarm("1", "Temperature", "26", "LOW", Long.toString(System.currentTimeMillis()));
                    System.out.println("Air conditioning OFF for temperature sensor 1");
                }
            }

            if (action!=null) {
                actionsProducer.sendAction(action);
                alarmsProducer.sendAlarm(alarm);
            }
        }
        catch (Exception e) {
        }

    }
    
}