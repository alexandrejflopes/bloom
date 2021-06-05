package ua.p50.manageApp.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.p50.manageApp.api.ExternalApi;
import ua.p50.manageApp.models.Sensor;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTests {
	
	@Mock
	private ExternalApi restUtil;

	@InjectMocks
	private SensorService sensorService;
	
	
	@Test
	public void whenGetLatestSensorZero_thenReturnLatestSensorReadingFromSensorZero() {
		Sensor s = new Sensor(0, "Double", "Temperature", "Celsius", "C", 26.0, 1);
		when(restUtil.getCurrentSensorInfo(0)).thenReturn(s);
		
		Sensor s_ret = sensorService.getLatestSensor(0);
		assertThat(s_ret).isEqualTo(s);	
	}
	
	
	@Test
	public void whenGetAllSensorZero_thenReturnAllSensorReadingsFromSensorZero() {
		Sensor s1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.0, 0);
		Sensor s2 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 24.5, 1);
		Sensor s3 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 25.0, 2);
		Sensor s4 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 26.0, 3);
		ArrayList<Sensor> al = new ArrayList<>();
		al.add(s1);
		al.add(s2);
		al.add(s3);
		al.add(s4);
		
		when(restUtil.getAllSensorInfo(0)).thenReturn(al);
		
		List<Sensor> l_ret = sensorService.getAllSensor(0);
		assertThat(l_ret).isEqualTo(al);	
	}
	
	@Test
	public void whenGetAllLatestSensors_thenReturnAllSensorReadingsFromAllSensors() {
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
		
		when(restUtil.getAllLatestSensorsInfo()).thenReturn(al);
		
		List<Sensor> l_ret = sensorService.getAllLatestSensors();
		assertThat(l_ret).isEqualTo(al);
	}
	
	@Test
	public void whenGetAllLatestSensorsOfTypeHumidity_thenReturnLatestSensorReadingsFromHumiditySensors() {
		Sensor s1 = new Sensor(2, "Double", "Humidity", "Percentage", "%", 65.0, 2);
		Sensor s2 = new Sensor(3, "Double", "Humidity", "Percentage", "%", 67.0, 3);
		Sensor s3 = new Sensor(4, "Double", "Humidity", "Percentage", "%", 70.0, 4);
		ArrayList<Sensor> al = new ArrayList<>();
		al.add(s1);
		al.add(s2);
		al.add(s3);
		
		when(restUtil.getAllLatestSensorsInfoPerType("Humidity")).thenReturn(al);
		
		List<Sensor> l_ret = sensorService.getAllLatestSensorsPerType("Humidity");
		assertThat(l_ret).isEqualTo(al);
	}
	
	
}
