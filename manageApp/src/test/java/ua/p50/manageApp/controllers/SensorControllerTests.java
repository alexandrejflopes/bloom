package ua.p50.manageApp.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.p50.manageApp.utils.Json.toJson;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

import ua.p50.manageApp.models.Sensor;
import ua.p50.manageApp.services.SensorService;

@WebMvcTest(SensorController.class)
public class SensorControllerTests {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private SensorService sensorService;
	
	@AfterEach
    public void after() {
        reset(sensorService);
    }
	
	@Test
	public void whenGettingLatestSensorReadingFromSensorZero_thenGetLatestSensorReadingFromSensorZero() throws JsonProcessingException, Exception {
		int sensor_id = 0;
		String endpoint = "/sensor/"+sensor_id+"/readings/latest";
		
		Sensor s = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		
		given(sensorService.getLatestSensor(sensor_id)).willReturn(s);
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(s)));
		 
		 verify(sensorService, VerificationModeFactory.times(1)).getLatestSensor(sensor_id);	
	}
	
	@Test
	public void whenGettingAllSensorReadingsFromSensorZero_thenGetAllSensorReadingsFromSensorZero() throws JsonProcessingException, Exception {
		int sensor_id = 0;
		int count = 4;
		String endpoint = "/sensor/"+sensor_id+"/readings/all/"+count;
		
		Sensor s1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		Sensor s2 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.5, 1);
		Sensor s3 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 25.0, 2);
		Sensor s4 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 26.0, 3);
		ArrayList<Sensor> al = new ArrayList<>();
		al.add(s1);
		al.add(s2);
		al.add(s3);
		al.add(s4);
		
		given(sensorService.getAllSensor(sensor_id)).willReturn(al);
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(al)));
		 
		 verify(sensorService, VerificationModeFactory.times(1)).getAllSensor(sensor_id);
	}
	
	@Test
	public void whenGettingallLatestSensorsReadings_thenGetAllLatestSensorReadings() throws JsonProcessingException, Exception {
		String endpoint = "/sensor/all/latest-readings";
		
		Sensor s1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		Sensor s2 = new Sensor(1, "Double", "Temperature", "Celsius", "C", 24.5, 1);
		Sensor s3 = new Sensor(2, "Double", "Humidity", "Percentage", "%", 65.0, 2);
		Sensor s4 = new Sensor(3, "Double", "Humidity", "Percentage", "%", 67.0, 3);
		Sensor s5 = new Sensor(4, "Double", "Humidity", "Percentage", "%", 70.0, 4);
		ArrayList<Sensor> al = new ArrayList<>();
		al.add(s1);
		al.add(s2);
		al.add(s3);
		al.add(s4);
		al.add(s5);
		
		given(sensorService.getAllLatestSensors()).willReturn(al);
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(al)));
		 
		 verify(sensorService, VerificationModeFactory.times(1)).getAllLatestSensors();
	}
	
	@Test
	public void whenGettingallLatestSensorsReadingsHumidity_thenGetAllLatestSensorReadingsHumidity() throws JsonProcessingException, Exception {
		String type = "Humidity";
		String endpoint = "/sensor/"+type+"/latest-readings";
		
		Sensor s1 = new Sensor(2, "Double", "Humidity", "Percentage", "%", 65.0, 2);
		Sensor s2 = new Sensor(3, "Double", "Humidity", "Percentage", "%", 67.0, 3);
		Sensor s3 = new Sensor(4, "Double", "Humidity", "Percentage", "%", 70.0, 4);
		ArrayList<Sensor> al = new ArrayList<>();
		al.add(s1);
		al.add(s2);
		al.add(s3);
		
		given(sensorService.getAllLatestSensorsPerType(type)).willReturn(al);
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(al)));
		 
		 verify(sensorService, VerificationModeFactory.times(1)).getAllLatestSensorsPerType(type);
	}
	
	@Test
	public void whenGettingSensorZeroInfo_thenGetSensorZeroInfo() throws JsonProcessingException, Exception {
		int sensor_id = 0;
		String endpoint = "/sensor/"+sensor_id;
		
		HashMap<String, String> info = new HashMap<>();
        info.put("sensorId", Integer.toString(sensor_id));
        info.put("dataType", "Double");
        info.put("sensorType", "Temperature");
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(info)));
	}
	
	@Test
	public void whenGettingSensorTwoInfo_thenGetSensorTwoInfo() throws JsonProcessingException, Exception {
		int sensor_id = 2;
		String endpoint = "/sensor/"+sensor_id;
		
		HashMap<String, String> info = new HashMap<>();
        info.put("sensorId", Integer.toString(sensor_id));
        info.put("dataType", "Double");
        info.put("sensorType", "Humidity");
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(info)));
	}
	
	@Test
	public void whenGettingSensorFiveInfo_thenGetSensorFiveInfo() throws JsonProcessingException, Exception {
		int sensor_id = 5;
		String endpoint = "/sensor/"+sensor_id;
		
		HashMap<String, String> info = new HashMap<>();
        info.put("sensorId", Integer.toString(sensor_id));
        info.put("dataType", "Double");
        info.put("sensorType", "Co2");
		
		 mvc.perform(get(endpoint)
				 .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(status()
						 .isOk())
		 .andExpect(content().json(toJson(info)));
	}

}
