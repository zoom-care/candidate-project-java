package com.zoomcare.candidatechallenge.rest;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

/**
 * Integration test of the Employee resource.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EmployeeResourceIntegrationTest {

    private final String API_URL = "http://localhost:8080/api/employee";

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testOkResultForAllEmployees() {
        ResponseEntity<ArrayList> responseEntity =  restTemplate.getForEntity(API_URL, ArrayList.class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void testOkResultForEmployeeById() {
        ResponseEntity<ArrayList> responseEntity =  restTemplate.getForEntity(API_URL + "/id/2", ArrayList.class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertEquals(1, responseEntity.getBody().size());
        Map employee = (Map) responseEntity.getBody().get(0);
        Assert.assertEquals(2, employee.get("id"));
    }

    @Test
    public void testNotFoundResultForEmployeeById() {
        ResponseEntity<Object> responseEntity =  restTemplate.getForEntity(API_URL + "/id/12", Object.class);
        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
    }

}
