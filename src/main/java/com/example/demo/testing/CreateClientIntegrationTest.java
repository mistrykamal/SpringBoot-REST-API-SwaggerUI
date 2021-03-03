package com.example.demo.testing;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.beans.Hospital;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment)
public class CreateClientIntegrationTest {
	@Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void createClient() {
        ResponseEntity<Client> responseEntity =
            restTemplate.postForEntity("/test/hospitals", new Hospital(1,"Test hospital","Chennai",3.9), Hospital.class);
        Hospital hosp = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Test hospital", hosp.getName());
    }
}
