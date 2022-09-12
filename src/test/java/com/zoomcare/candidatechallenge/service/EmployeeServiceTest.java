package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.CandidateChallengeApplication;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.output.EmployeeDTO;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CandidateChallengeApplication.class)
public class EmployeeServiceTest {
    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    private Employee employee;

    @Before
    public void setup() {
        // Initialize test data
        HashMap<String, String> properties = new HashMap<>();
        properties.put("title", "CEO");
        List<Employee> employees = new ArrayList<>();
        Employee subEmployee = new Employee(2, new Employee(), Collections.emptyList(), Collections.emptyMap());
        employees.add(subEmployee);

        this.employee = new Employee(1, null, employees, properties);
        subEmployee.setSupervisor(employee);
    }

    @Test
    public void getById(){
        when(employeeRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.employee));

        EmployeeDTO returnedEmployee = employeeService.getById(any(Integer.class));

        Assert.assertEquals((Integer) 1, returnedEmployee.getId());
        Assert.assertEquals(1, returnedEmployee.getDirectEmployees().size());
        Assert.assertEquals(1, returnedEmployee.getProperties().size());
        Assert.assertEquals("CEO", returnedEmployee.getProperties().get("title"));
    }

    @Test
    public void getAllTopLevelEmployees(){
        List<Employee> employees = Stream.of(employee).collect(Collectors.toList());
        when(employeeRepository.findBySupervisorIdIsNull()).thenReturn(employees);

        List<EmployeeDTO> returnedEmployees = employeeService.getAllTopLevelEmployees();

        Assert.assertEquals(1, returnedEmployees.size());
        Assert.assertEquals(null, returnedEmployees.get(0).getSupervisorId());
    }
}
