package ua.p50.sensorsApp.models;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "temperature")
public class Temperature {

    @Column(name = "time")
    private Instant time;
    
    @Column(name = "id")
    private int id;

    @Column(name = "dataType")
    private String dataType;

    @Column(name = "sensorType")
    private String sensorType;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unitAbreviation")
    private String unitAbreviation;

    @Column(name = "value")
    private double value;

    @Column(name = "timestamp")
    private int timestamp;

    public Temperature() {

    }

    public Temperature(int id, String dataType, String sensorType, String unit, String unitAbreviartion, double value, int timestamp) {
        this.id = id;
        this.dataType = dataType;
        this.sensorType = sensorType;
        this.unit = unit;
        this.unitAbreviation = unitAbreviartion;
        this.value = value;
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

    public String getUnitAbreviation() {
        return this.unitAbreviation;
    }

    public void setUnitAbreviation(String unitAbreviation) {
        this.unitAbreviation = unitAbreviation;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

}
