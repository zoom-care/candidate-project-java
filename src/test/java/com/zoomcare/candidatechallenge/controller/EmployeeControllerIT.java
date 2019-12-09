package com.zoomcare.candidatechallenge.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import com.zoomcare.candidatechallenge.model.Employee;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerIT extends BaseControllerIT {

    @Test
    public void testKnownEmployee() throws Exception {
        ResponseEntity<Employee> responseEntity = template.getForEntity(employeeEndpoint.toString().concat("/1"), Employee.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Employee employee = responseEntity.getBody();
        assertThat(employee, notNullValue());
        assertThat(employee.getId(), notNullValue());
        assertThat(employee.getId(), equalTo(1L));
    }

    @Test
    public void testUnknownEmployee() throws Exception {
        ResponseEntity<Employee> responseEntity = template.getForEntity(employeeEndpoint.toString().concat("/-1"), Employee.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        Employee employee = responseEntity.getBody();
        assertThat(employee, nullValue());
    }

    @Test
    public void testRetrieveTopLevel() throws Exception {
        ResponseEntity<List> responseEntity = template.getForEntity(employeeEndpoint.toString().concat("/toplevel"), List.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        List employees = responseEntity.getBody();
        assertThat(employees, notNullValue());
        assertThat(employees.isEmpty(), is(false));
    }
}