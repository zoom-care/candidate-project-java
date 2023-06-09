package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.EmployeeResponse;
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
        EmployeeResponse employeeResponse = getEmployeeResponse(1L, null,"title", "CEO");
        Mockito.when(service.getEmployee(Mockito.any())).thenReturn(employeeResponse);
        EmployeeResponse controllerResponse = controller.getEmployee(1L);
        assertNotNull(employeeResponse);
        assertEquals(employeeResponse, controllerResponse);
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
        EmployeeResponse employeeResponse = getEmployeeResponse(0L, null, null, null);
        Mockito.when(service.getEmployee(Mockito.any())).thenReturn(employeeResponse);
        EmployeeResponse controllerResponse = controller.getEmployee(0L);
        assertNotNull(employeeResponse);
        assertEquals(employeeResponse, controllerResponse);
    }

    private static EmployeeResponse getEmployeeResponse(Long id, Long supervisorId, String key, String title) {
        Property property = new Property();
        property.setEmployeeId(id);
        property.setKey(key);
        property.setValue(title);
        List<Property> propertyByEmployeeId = new ArrayList<>();
        propertyByEmployeeId.add(property);
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(id)
                .supervisorId(supervisorId)
                .propertyList(propertyByEmployeeId)
                .build();
        return employeeResponse;
    }

}