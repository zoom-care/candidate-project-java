package com.zoomcare.candidatechallenge.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {
  @InjectMocks private EmployeeRepository employeeRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getEmployeeById() {
    List<EmployeePropertyDto> employeeList = employeeRepository.getEmployeeById(3L);
    assertNotNull(employeeList);
    assertEquals(15, employeeList.size());
  }

  @Test
  public void getTopLevelEmployees() {
    List<EmployeePropertyDto> employeeList = employeeRepository.getTopLevelEmployees();
    assertNotNull(employeeList);
    assertEquals(1, employeeList.size());
  }
}
