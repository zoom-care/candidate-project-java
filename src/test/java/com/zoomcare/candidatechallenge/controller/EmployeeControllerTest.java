package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeResponse;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.service.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmployeeControllerTest {

    private EmployeeController controller;
    EmployeeServiceImpl service;

    @Before
    public void setUp() {
        service = Mockito.mock(EmployeeServiceImpl.class);
        controller = new EmployeeController(service);
    }

    @Test
    public void test_getEmployee_success() {
        EmployeeResponse employeeResponse = getEmployeeResponse(1L, null, "title", "CEO");
        Mockito.when(service.getEmployee(Mockito.any())).thenReturn(Optional.ofNullable(employeeResponse));
        ResponseEntity<EmployeeResponse> controllerResponse = controller.getEmployee(1L);
        assertNotNull(controllerResponse);
        assertEquals(employeeResponse, controllerResponse.getBody());
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
    }

    @Test
    public void test_getEmployees_success() {
        List<EmployeeResponse> employeesResponse = new ArrayList<>();
        Mockito.when(service.getEmployees()).thenReturn(employeesResponse);
        List<EmployeeResponse> controllerResponse = controller.getEmployees();
        assertNotNull(employeesResponse);
        assertEquals(employeesResponse, controllerResponse);
    }

    @Test
    public void test_getEmployee_notFound() {
        Mockito.when(service.getEmployee(Mockito.any())).thenReturn(Optional.empty());
        ResponseEntity<EmployeeResponse> controllerResponse = controller.getEmployee(0L);
        assertNull(controllerResponse.getBody());
        assertEquals(HttpStatus.NOT_FOUND, controllerResponse.getStatusCode());
    }

    private EmployeeResponse getEmployeeResponse(Long id, Long supervisorId, String key, String title) {
        Property property = new Property();
        property.setEmployeeId(id);
        property.setKey(key);
        property.setValue(title);
        List<Property> propertyByEmployeeId = new ArrayList<>();
        propertyByEmployeeId.add(property);
        List<Employee> reporterList = new ArrayList<>();
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setSupervisorId(1L);
        Employee employee6 = new Employee();
        employee6.setId(6L);
        employee6.setSupervisorId(1L);
        Employee employee7 = new Employee();
        employee7.setId(7L);
        employee7.setSupervisorId(1L);
        reporterList.add(employee2);
        reporterList.add(employee6);
        reporterList.add(employee7);
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(id)
                .supervisorId(supervisorId)
                .propertyList(propertyByEmployeeId)
                .reporterList(reporterList)
                .build();
        return employeeResponse;
    }

}