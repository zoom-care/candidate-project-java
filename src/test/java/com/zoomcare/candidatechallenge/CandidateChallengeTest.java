package com.zoomcare.candidatechallenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CandidateChallengeTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void finEmployeeTest() {

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/zoomcare/employees" + "/8", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getTopEmployeesTest() {

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/zoomcare/topEmployees", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
