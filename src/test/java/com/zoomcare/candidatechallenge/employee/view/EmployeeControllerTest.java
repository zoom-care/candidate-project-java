package com.zoomcare.candidatechallenge.employee.view;

import com.zoomcare.candidatechallenge.employee.business.Employee;
import com.zoomcare.candidatechallenge.employee.business.EmployeeService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeServiceMock;

    EmployeeController employeeControllerUnderTest;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void employees() {
        Employee employee1 = Employee.builder()
                .id(1L)
                .directReports(new ArrayList<>())
                .properties(new ArrayList<>())
                .build();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        when(employeeServiceMock.getEmployees()).thenReturn(employees);
        EmployeeController employeeControllerUnderTest = new EmployeeController(employeeServiceMock);
        List<EmployeeViewModel> results = employeeControllerUnderTest.employees();
        if(results == null ){
            Assert.fail("Results were null.");
        }
        Assert.assertNotEquals(null, results);
        Assert.assertEquals(1, results.size());
        Long expectedFirstId = 1L;
        Assert.assertEquals(expectedFirstId, results.stream().findFirst().map(EmployeeViewModel::getId).orElse(0L));
    }
}