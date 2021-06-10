package ua.p50.manageApp.integration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import io.cucumber.spring.CucumberContextConfiguration;
import ua.p50.manageApp.ManageAppApplication;
import ua.p50.manageApp.services.SensorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageAppApplication.class, webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public abstract class SpringIntegrationTest {

	@MockBean
	private SensorService sensorService;
	
	protected RestTemplate restTemplate = new RestTemplate();

	static ResponseEntity<String> latestResponse = null;
	
	public void executeGet(String url) throws IOException {
        latestResponse = restTemplate.getForEntity(url, String.class);
    }
	
}
