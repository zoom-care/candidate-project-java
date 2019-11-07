package com.zoomcare.candidatechallenge.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.zoomcare.candidatechallenge.service.impl.EmployeeServiceImpl;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
  @InjectMocks private EmployeeController employeeController;

  @Mock private EmployeeServiceImpl employeeService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    when(employeeService.getEmployeeById(2L)).thenReturn(new ArrayList<>());
  }

  @Test
  public void getEmployeeById() {
    ResponseEntity responseEntity = employeeController.getEmployeeById(2L);
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getTopLevelEmployees() {
    ResponseEntity responseEntity = employeeController.getTopLevelEmployees();
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
}
