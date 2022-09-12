package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.Employee;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeControllerTest {

    @Test(timeout=1000)
    public void testFindAllSupervisors() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Employee>> employeeResponses = restTemplate.exchange(
                "http://localhost:8080/employee/supervisors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        );
        String supervisors = employeeResponses.getBody().toString();

        assertEquals("[Employee{id='1', supervisorId='0'}, Employee{id='2', supervisorId='1'}, Employee{id='6', supervisorId='1'}, Employee{id='7', supervisorId='1'}]", supervisors);
    }

    @Test(timeout=1000)
    public void testFindBySupervisorId() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Employee>> employeeResponses = restTemplate.exchange(
                "http://localhost:8080/employee/3/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        );
        String employees = employeeResponses.getBody().toString();

        assertEquals("[Employee{id='4', supervisorId='3'}]", employees);
    }

    @Test(timeout=1000)
    public void testFindBySupervisorIdDNE() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Employee>> employeeResponses = restTemplate.exchange(
                "http://localhost:8080/employee/99/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        );
        String employees = employeeResponses.getBody().toString();

        assertEquals("[]", employees);
    }
}
