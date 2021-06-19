package ua.p50.detectorApp.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.p50.detectorApp.api.ExternalApi;
import ua.p50.detectorApp.kafka.ActionsProducer;
import ua.p50.detectorApp.kafka.AlarmsProducer;
import ua.p50.detectorApp.models.Action;
import ua.p50.detectorApp.models.Alarm;
import ua.p50.detectorApp.models.Sensor;

@ExtendWith(MockitoExtension.class)
public class DetectorServiceTests {
	
	@Captor
	private ArgumentCaptor<Action> action_captor;
	
	@Captor
	private ArgumentCaptor<Alarm> alarm_captor;
	
	@Mock
	private ExternalApi restUtil;

	@Mock
	private ActionsProducer actionsProducer;
	
	@Mock
	private AlarmsProducer alarmsProducer;
	
	@InjectMocks
	private DetectorService detectorService;

	
	@Test
	public void  whenTempAboveLimit_and_inspectTemperatureSensor0_thenProduceActionAndAlarm_and_ActivateAirConditioning() {
		Sensor s = new Sensor(0, "Double", "Temperature", "Celsius", "C", 27.0, 1);
		when(restUtil.getCurrentSensorInfo(0)).thenReturn(s);
		
		detectorService.inspectTemperatureSensor0();
		
		verify(actionsProducer, times(1)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(0);
		assertThat(act_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(act_cap.getAction()).isEqualTo("airConditioningOn");
		
		verify(alarmsProducer, times(1)).sendAlarm(alarm_captor.capture());		
	
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(0);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(alarm_cap.getValue()).isEqualTo("26.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("HIGH");
	}
	
	@Test
	public void  whenTempBellowLimit_and_inspectTemperatureSensor0_and_AriCondAct_thenProduceActionAndAlarm_and_turnOffAriCond() {
		Sensor s1 = new Sensor(0, "Double", "Temperature", "Celsius", "C", 27.0, 1);
		when(restUtil.getCurrentSensorInfo(0)).thenReturn(s1);
		
		detectorService.inspectTemperatureSensor0();
		
		Sensor s = new Sensor(0, "Double", "Temperature", "Celsius", "C", 23.0, 1);
		when(restUtil.getCurrentSensorInfo(0)).thenReturn(s);
		
		detectorService.inspectTemperatureSensor0();
		
		verify(actionsProducer, times(2)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(0);
		assertThat(act_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(act_cap.getAction()).isEqualTo("airConditioningOff");
		
		verify(alarmsProducer, times(2)).sendAlarm(alarm_captor.capture());		
	
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(0);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(alarm_cap.getValue()).isEqualTo("24.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("LOW");

	}
	
	@Test
	public void whenTempAboveLimit_and_inspectTemperatureSensor1_thenProduceActionAndAlarm_and_ActivateAirConditioning() {
		Sensor s = new Sensor(1, "Double", "Temperature", "Celsius", "C", 27.0, 1);
		when(restUtil.getCurrentSensorInfo(1)).thenReturn(s);
		
		detectorService.inspectTemperatureSensor1();
		
		verify(actionsProducer, times(1)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(1);
		assertThat(act_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(act_cap.getAction()).isEqualTo("airConditioningOn");
		
		verify(alarmsProducer, times(1)).sendAlarm(alarm_captor.capture());		
		
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(1);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(alarm_cap.getValue()).isEqualTo("26.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("HIGH");

	}
	
	@Test
	public void  whenTempBellowLimit_and_inspectTemperatureSensor1_and_AirCondAct_thenProduceActionAndAlarm_and_turnOffAriCond() {
		Sensor s1 = new Sensor(1, "Double", "Temperature", "Celsius", "C", 27.0, 1);
		when(restUtil.getCurrentSensorInfo(1)).thenReturn(s1);
		
		detectorService.inspectTemperatureSensor1();
		
		Sensor s = new Sensor(1, "Double", "Temperature", "Celsius", "C", 23.0, 1);
		when(restUtil.getCurrentSensorInfo(1)).thenReturn(s);
		
		detectorService.inspectTemperatureSensor1();
		
		verify(actionsProducer, times(2)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(1);
		assertThat(act_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(act_cap.getAction()).isEqualTo("airConditioningOff");
		
		verify(alarmsProducer, times(2)).sendAlarm(alarm_captor.capture());		
	
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(1);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Temperature");
		assertThat(alarm_cap.getValue()).isEqualTo("24.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("LOW");
		
	}
	
	
	@Test
	public void whenHumidityAboveLimit_and_inspectHumiditySensor2_thenProduceActionAndAlarm_and_ActivateWatering() {
		detectorService.setHumidityMax(90.0);
		detectorService.setHumidityMin(50.0);
		
		Sensor s1 = new Sensor(2, "Double", "Humidity", "Percentage", "%", 49.0, 1);
		when(restUtil.getCurrentSensorInfo(2)).thenReturn(s1);
		
		detectorService.inspectHumiditySensor2();
		
		Sensor s = new Sensor(2, "Double", "Humidity", "Percentage", "%", 91.0, 1);
		when(restUtil.getCurrentSensorInfo(2)).thenReturn(s);
		
		detectorService.inspectHumiditySensor2();
		
		verify(actionsProducer, times(2)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(2);
		assertThat(act_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(act_cap.getAction()).isEqualTo("wateringOff");
		
		verify(alarmsProducer, times(2)).sendAlarm(alarm_captor.capture());		
	
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(2);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(alarm_cap.getValue()).isEqualTo("90.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("HIGH");
	}
	
	@Test
	public void  whenHumBellowLimit_and_inspectHumifitySensor2_and_WaterAct_thenProduceActionAndAlarm_and_turnOffWatering() {
		detectorService.setHumidityMax(90.0);
		detectorService.setHumidityMin(50.0);
		
		Sensor s = new Sensor(2, "Double", "Humidity", "Percentage", "%", 49.0, 1);
		when(restUtil.getCurrentSensorInfo(2)).thenReturn(s);
		
		detectorService.inspectHumiditySensor2();
		
		verify(actionsProducer, times(1)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(2);
		assertThat(act_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(act_cap.getAction()).isEqualTo("wateringOn");
		
		verify(alarmsProducer, times(1)).sendAlarm(alarm_captor.capture());
		
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(2);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(alarm_cap.getValue()).isEqualTo("50.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("LOW");
	}
	
	@Test
	public void whenHumidityAboveLimit_and_inspectHumiditySensor3_thenProduceActionAndAlarm_and_ActivateWatering() {
		detectorService.setHumidityMax(90.0);
		detectorService.setHumidityMin(50.0);
		
		Sensor s1 = new Sensor(3, "Double", "Humidity", "Percentage", "%", 49.0, 1);
		when(restUtil.getCurrentSensorInfo(3)).thenReturn(s1);
		
		detectorService.inspectHumiditySensor3();
		
		Sensor s = new Sensor(3, "Double", "Humidity", "Percentage", "%", 91.0, 1);
		when(restUtil.getCurrentSensorInfo(3)).thenReturn(s);
		
		detectorService.inspectHumiditySensor3();
		
		verify(actionsProducer, times(2)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(3);
		assertThat(act_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(act_cap.getAction()).isEqualTo("wateringOff");
		
		verify(alarmsProducer, times(2)).sendAlarm(alarm_captor.capture());		
	
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(3);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(alarm_cap.getValue()).isEqualTo("90.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("HIGH");
	}
	
	@Test
	public void  whenHumBellowLimit_and_inspectHumifitySensor3_and_WaterAct_thenProduceActionAndAlarm_and_turnOffWatering() {
		detectorService.setHumidityMax(90.0);
		detectorService.setHumidityMin(50.0);
		
		Sensor s = new Sensor(3, "Double", "Humidity", "Percentage", "%", 49.0, 1);
		when(restUtil.getCurrentSensorInfo(3)).thenReturn(s);
		
		detectorService.inspectHumiditySensor3();
		
		verify(actionsProducer, times(1)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(3);
		assertThat(act_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(act_cap.getAction()).isEqualTo("wateringOn");
		
		verify(alarmsProducer, times(1)).sendAlarm(alarm_captor.capture());
		
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(3);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(alarm_cap.getValue()).isEqualTo("50.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("LOW");
	}
	
	@Test
	public void whenHumidityAboveLimit_and_inspectHumiditySensor4_thenProduceActionAndAlarm_and_ActivateWatering() {
		detectorService.setHumidityMax(90.0);
		detectorService.setHumidityMin(50.0);
		
		Sensor s1 = new Sensor(4, "Double", "Humidity", "Percentage", "%", 49.0, 1);
		when(restUtil.getCurrentSensorInfo(4)).thenReturn(s1);
		
		detectorService.inspectHumiditySensor4();
		
		Sensor s = new Sensor(4, "Double", "Humidity", "Percentage", "%", 91.0, 1);
		when(restUtil.getCurrentSensorInfo(4)).thenReturn(s);
		
		detectorService.inspectHumiditySensor4();
		
		verify(actionsProducer, times(2)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(4);
		assertThat(act_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(act_cap.getAction()).isEqualTo("wateringOff");
		
		verify(alarmsProducer, times(2)).sendAlarm(alarm_captor.capture());		
	
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(4);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(alarm_cap.getValue()).isEqualTo("90.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("HIGH");
	}
	
	@Test
	public void  whenHumBellowLimit_and_inspectHumifitySensor4_and_WaterAct_thenProduceActionAndAlarm_and_turnOffWatering() {
		detectorService.setHumidityMax(90.0);
		detectorService.setHumidityMin(50.0);
		
		Sensor s = new Sensor(4, "Double", "Humidity", "Percentage", "%", 49.0, 1);
		when(restUtil.getCurrentSensorInfo(4)).thenReturn(s);
		
		detectorService.inspectHumiditySensor4();
		
		verify(actionsProducer, times(1)).sendAction(action_captor.capture());
		
		Action act_cap = action_captor.getValue();
		assertThat(act_cap.getSensorId()).isEqualTo(4);
		assertThat(act_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(act_cap.getAction()).isEqualTo("wateringOn");
		
		verify(alarmsProducer, times(1)).sendAlarm(alarm_captor.capture());
		
		Alarm alarm_cap = alarm_captor.getValue();
		assertThat(alarm_cap.getSensorId()).isEqualTo(4);
		assertThat(alarm_cap.getSensorType()).isEqualTo("Humidity");
		assertThat(alarm_cap.getValue()).isEqualTo("50.0");
		assertThat(alarm_cap.getAlarm()).isEqualTo("LOW");
	}
	
}
