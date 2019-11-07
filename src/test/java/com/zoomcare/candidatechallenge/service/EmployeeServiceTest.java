package com.zoomcare.candidatechallenge.service;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.impl.EmployeeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeServiceImpl employeeService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    when(employeeRepository.getEmployeeById(2L)).thenReturn(new ArrayList<>());
  }

  @Test
  public void getEmployeeById() {
    List<EmployeePropertyDto> employeeList = employeeService.getEmployeeById(2L);
    assertNotNull(employeeList);
  }

  @Test
  public void getTopLevelEmployees() {
    List<EmployeePropertyDto> employeeList = employeeService.getTopLevelEmployees();
    assertNotNull(employeeList);
  }
}
