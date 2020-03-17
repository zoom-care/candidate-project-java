package com.zoomcare.candidatechallenge.employee.view;

import com.zoomcare.candidatechallenge.employee.business.Employee;
import com.zoomcare.candidatechallenge.employee.business.EmployeeService;
import com.zoomcare.candidatechallenge.employee.business.Property;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeServiceMock;

    @Test
    public void getEmployees() {
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
    @Test
    public void getSingleEmployee() {
        Property property1Employee2 = Property.builder()
                .employeeId(2L)
                .key("-test-key-2-")
                .value("-test-value-2-")
                .build();
        List<Property> employee2Properties = new ArrayList<>();
        employee2Properties.add(property1Employee2);
        Employee employee2 = Employee.builder()
                .id(2L)
                .supervisorId(1L)
                .directReports(new ArrayList<>())
                .properties(employee2Properties)
                .build();
        Property property1Employee1 = Property.builder()
                .employeeId(1L)
                .key("-test-key-1-")
                .value("-test-value-1-")
                .build();
        List<Property> employee1Properties = new ArrayList<>();
        employee1Properties.add(property1Employee1);
        List<Employee> employee1DirectReports = new ArrayList<>();
        employee1DirectReports.add(employee2);
        Employee employee1 = Employee.builder()
                .id(1L)
                .directReports(employee1DirectReports)
                .properties(employee1Properties)
                .build();
        when(employeeServiceMock.getEmployee(1L)).thenReturn(Optional.of(employee1));
        EmployeeController employeeControllerUnderTest = new EmployeeController(employeeServiceMock);
        EmployeeViewModel results = employeeControllerUnderTest.employee(1L);
        if(results == null ){
            Assert.fail("Results were null.");
        }
        Assert.assertNotEquals(null, results);
        Long expectedFirstId = 1L;
        Assert.assertEquals(expectedFirstId, results.getId());
        Assert.assertEquals(property1Employee1.getKey(), results.getProperties().stream().findFirst().map(PropertyViewModel::getKey).orElse(("")));
        Assert.assertEquals(property1Employee1.getValue(), results.getProperties().stream().findFirst().map(PropertyViewModel::getValue).orElse(("")));
        Assert.assertEquals(property1Employee1.getEmployeeId(), results.getProperties().stream().findFirst().map(PropertyViewModel::getEmployeeId).orElse((0L)));
        Optional<EmployeeViewModel> employee2Result =      results.getDirectReports().stream().findFirst();
        Assert.assertEquals(employee2.getId(), employee2Result.map(EmployeeViewModel::getId).orElse(0L));
        Assert.assertEquals(employee2.getSupervisorId(), employee2Result.map(EmployeeViewModel::getSupervisorId).orElse(0L));
    }
}