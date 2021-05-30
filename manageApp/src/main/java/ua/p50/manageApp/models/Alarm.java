package ua.p50.manageApp.models;

public class Alarm {

    private String sensorId;
    private String sensorType;
    private String value;
    private String alarm;
    private String timestamp;

    public Alarm(String sensorId, String sensorType, String value, String alarm, String timestamp) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.timestamp = timestamp;
        this.alarm = alarm;
        this.value = value;
    }

    public int getSensorId() {
        return Integer.parseInt(this.sensorId);
    }

    public void setSensorId(String id) {
        this.sensorId = id;
    }

    public String getAlarm() {
        return this.alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSensorType() {
        return this.sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public String toString() {
        return this.sensorId + "-" + this.sensorType + "-" + this.value + "-" + this.alarm + "-" + this.timestamp;
    }

}