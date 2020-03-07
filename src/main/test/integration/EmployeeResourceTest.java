package integration;


import com.zoomcare.candidatechallenge.config.DbConfig;
import com.zoomcare.candidatechallenge.config.JerseyConfig;
import com.zoomcare.candidatechallenge.model.ClientEmployee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertiesRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.service.PropertiesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes= {JerseyConfig.class, DbConfig.class, EmployeeService.class, EmployeeRepository.class, PropertiesService.class, PropertiesRepository.class})
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
public class EmployeeResourceTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testEmployeeGet() {
        String url = String.format("http://localhost:%s/employee?id=2", String.valueOf(port));
        ResponseEntity<ClientEmployee> entity = this.restTemplate
                .getForEntity(url, ClientEmployee.class);
        ClientEmployee clientEmployee = entity.getBody();
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertEquals(new BigInteger("2"), clientEmployee.getId());
    }

    @Test
    public void testEmployeeGetWithInvalidId() {
        String url = String.format("http://localhost:%s/employee?id=asf", String.valueOf(port));
        ResponseEntity<String> entity = this.restTemplate
                .getForEntity(url, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

}
