package ua.p50.detectorApp.models;

public class Action {

    private String sensorId;
    private String sensorType;
    private String timestamp;
    private String action;

    public Action(String sensorId, String sensorType, String timestamp, String action) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.timestamp = timestamp;
        this.action = action;
    }

  
    public int getSensorId() {
        return Integer.parseInt(this.sensorId);
    }

    public void setSensorId(String id) {
        this.sensorId = id;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSensorType() {
        return this.sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public int getTimestamp() {
        return Integer.parseInt(this.timestamp);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return this.sensorId + "-" + this.sensorType + "-" + this.timestamp + "-" + this.action;
    }
}
