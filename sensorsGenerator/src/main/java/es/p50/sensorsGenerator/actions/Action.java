package es.p50.sensorsGenerator.actions;

public class Action {

    private String sensorId;
    private String sensorType;
    private String timestamp;
    private String action;

  
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
        return "Action {"
            + "sensorId = " + sensorId
            + ", sensorType = '" + this.sensorType + "'"
            + ", action = '" + this.action + "'"
            + ", timestamp = '" + this.timestamp + "'"
            + "}";
    }
}
