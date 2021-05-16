package es.p50.sensorsGenerator.temperature;

public class Temperature {

    private int id;
    private String dataType;
    private String sensorType;
    private String unit;
    private char unitAbreviation;
    private double value;
    private int timestamp;

    public Temperature(int id, String dataType, String sensorType, String unit, char unitAbreviation, int value, int timestamp) {
        this.id = id;
        this.dataType = dataType;
        this.sensorType = sensorType;
        this.unit = unit;
        this.unitAbreviation = unitAbreviation;
        this.value = (double) value/10.0;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSensorType() {
        return this.sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public char getUnitAbreviation() {
        return this.unitAbreviation;
    }

    public void setUnitAbreviation(char unitAbreviation) {
        this.unitAbreviation = unitAbreviation;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = (double) value/10.0;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
