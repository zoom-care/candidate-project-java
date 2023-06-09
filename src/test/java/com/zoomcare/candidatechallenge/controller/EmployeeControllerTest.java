package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.service.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeControllerTest {

    private EmployeeController controller;
    EmployeeService service;

    @Before
    public void setUp() {
        service = Mockito.mock(EmployeeServiceImpl.class);
        controller = new EmployeeController(service);
    }

    @Test
    public void test_getEmployee_success() {
        Employee employee = getEmployee(1L, null,"title", "CEO");
        Mockito.when(service.getEmployee(Mockito.any())).thenReturn(employee);
        Employee controllerResponse = controller.getEmployee(1L);
        assertNotNull(employee);
        assertEquals(employee, controllerResponse);
    }

    @Test
    public void test_getEmployees_success() {
        List<Employee> employeesResponse = new ArrayList<>();
        Mockito.when(service.getEmployees()).thenReturn(employeesResponse);
        List<Employee> controllerResponse = controller.getEmployees();
        assertNotNull(employeesResponse);
        assertEquals(employeesResponse, controllerResponse);
    }

    @Test
    public void test_getEmployee_notFound() {
        Employee Employee = getEmployee(0L, null, null, null);
        Mockito.when(service.getEmployee(Mockito.any())).thenReturn(Employee);
        Employee controllerResponse = controller.getEmployee(0L);
        assertNotNull(Employee);
        assertEquals(Employee, controllerResponse);
    }

    private static Employee getEmployee(Long id, Long supervisorId, String key, String title) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setSupervisorId(supervisorId);
        Property property = new Property();
        property.setEmployee(employee);
        property.setKey(key);
        property.setValue(title);
        List<Property> propertyByEmployeeId = new ArrayList<>();
        propertyByEmployeeId.add(property);
        return employee;
    }

}