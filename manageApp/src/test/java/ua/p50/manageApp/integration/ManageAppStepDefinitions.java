package ua.p50.manageApp.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ua.p50.manageApp.models.Sensor;
import ua.p50.manageApp.services.SensorService;
import ua.p50.manageApp.utils.Json;

public class ManageAppStepDefinitions extends SpringIntegrationTest {
	
	@Autowired
	private SensorService sensorService;
	
	private Sensor sensor0;
	
	private ArrayList<Sensor> allSensor0Readings;
	
	private ArrayList<Sensor> allLatestReadings;
	
	private HashMap<String, String> sensorInfo;
	
	@Before
	public void initVariables() {
		sensor0 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		
		Sensor s1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		Sensor s2 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.5, 1);
		Sensor s3 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 25.0, 2);
		Sensor s4 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 26.0, 3);
		
		allSensor0Readings = new ArrayList<>();
		allSensor0Readings.add(s1);
		allSensor0Readings.add(s2);
		allSensor0Readings.add(s3);
		allSensor0Readings.add(s4);
		
		s1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		s2 = new Sensor(1, "Double", "Temperature", "Celsius", "C", 24.5, 1);
		s3 = new Sensor(2, "Double", "Humidity", "Percentage", "%", 65.0, 2);
		s4 = new Sensor(3, "Double", "Humidity", "Percentage", "%", 67.0, 3);
		Sensor s5 = new Sensor(4, "Double", "Humidity", "Percentage", "%", 70.0, 4);
		
		allLatestReadings = new ArrayList<>();
		allLatestReadings.add(s1);
		allLatestReadings.add(s2);
		allLatestReadings.add(s3);
		allLatestReadings.add(s4);
		allLatestReadings.add(s5);
		
		sensorInfo = new HashMap<>();
		sensorInfo.put("sensorId", "0");
		sensorInfo.put("dataType", "Double");
		sensorInfo.put("sensorType", "Temperature");
	}
	
	public void getSensorInfo(int id) {
		sensorInfo.put("sensorId", Integer.toString(id));
		if(id==0||id==1) sensorInfo.put("sensorType", "Temperature");
        else if(id==2||id==3||id==4) sensorInfo.put("sensorType", "Humidity");
        else sensorInfo.put("sensorType", "Co2");
	}

	@When("client calls the endpoint of the latest reading of sensor {int}")
    public void GettingLatestSensorReadingFromSensor(Integer sensorId) throws Throwable {
		String endpoint = "http://localhost:50060/sensor/"+sensorId+"/readings/latest";
		
		sensor0.setId(sensorId);
		given(sensorService.getLatestSensor(sensorId)).willReturn(sensor0);
		
		executeGet(endpoint);
    }
	
	@Then("the client recieves a reply with code {int}")
	public void GetReplyCode(Integer code) {
		Integer i = latestResponse.getStatusCode().value();
		assertThat(i).isEqualTo(code);	
	}
	
	@And("receives the latest reading of sensor {int}")
	public void GetLatestSensorReadingFromSensor(Integer sensorId) throws JsonProcessingException {
		String recv_json = latestResponse.getBody();
		sensor0.setId(sensorId);
		String  sensor_str = Json.toJson(sensor0);
		
		assertThat(recv_json).isEqualTo(sensor_str);	
	}
	
	@When("client calls the endpoint of all the readings of sensor {int} with count {int}")
    public void GettingAllSensorReadingsFromSensor(Integer sensorId, Integer count) throws Throwable {
		String endpoint = "http://localhost:50060/sensor/"+sensorId+"/readings/all/"+count;
		
		given(sensorService.getAllSensor(sensorId)).willReturn(allSensor0Readings);
		
		executeGet(endpoint);
    }
	
	@And("receives all the readings of sensor {int}")
	public void GetAllSensorReadingsFromSensor(Integer sensorId) throws JsonProcessingException {
		String recv_json = latestResponse.getBody();
		String  sensor_str = Json.toJson(allSensor0Readings);
		
		assertThat(recv_json).isEqualTo(sensor_str);	
	}
	
	@When("client calls the endpoint of all the latest readings")
    public void GettingallLatestSensorsReadings() throws Throwable {
		String endpoint = "http://localhost:50060/sensor/all/latest-readings";
		given(sensorService.getAllLatestSensors()).willReturn(allLatestReadings);
		
		executeGet(endpoint);
    }
	
	@And("receives all the latest readings")
	public void GetAllLatestSensorReadings() throws JsonProcessingException {
		String recv_json = latestResponse.getBody();
		String  sensor_str = Json.toJson(allLatestReadings);
		
		assertThat(recv_json).isEqualTo(sensor_str);
	}
	
	@When("client calls the endpoint with the info on sensor {int}")
    public void GettingSensorInfo(Integer sensorId) throws Throwable {
		String endpoint = "http://localhost:50060/sensor/"+sensorId;
		
		executeGet(endpoint);
    }
	
	@And("receives the info on sensor {int}")
	public void GetSensorInfo(Integer sensorId) throws JsonProcessingException {
		String recv_json = latestResponse.getBody();
		getSensorInfo(sensorId);
		String  sensor_str = Json.toJson(sensorInfo);
		
		assertThat(recv_json).isEqualTo(sensor_str);
	}
	
	
	
}
