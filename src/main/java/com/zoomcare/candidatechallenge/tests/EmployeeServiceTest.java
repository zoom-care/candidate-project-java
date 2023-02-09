package com.zoomcare.candidatechallenge.tests;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.models.Property;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import com.zoomcare.candidatechallenge.repositories.PropertyRepository;
import com.zoomcare.candidatechallenge.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PropertyRepository propertyRepository;

    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeRepository, propertyRepository);
    }

    @Test
    public void testGetEmployeeById() {
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setSupervisorId(null);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        List<Property> properties = new ArrayList<>();
        Property property = new Property();
        property.setEmployeeId(id);
        property.setKey("testdata");
        property.setValue("true");
        properties.add(property);
        when(propertyRepository.findByEmployeeId(anyLong())).thenReturn(Optional.of(properties));

        Employee directReport1 = new Employee();
        directReport1.setId(2L);
        directReport1.setSupervisorId(id);
        Employee directReport2 = new Employee();
        directReport2.setId(3L);
        directReport2.setSupervisorId(id);
        List<Employee> directReports = new ArrayList<>();
        directReports.add(directReport1);
        directReports.add(directReport2);
        when(employeeRepository.findDirectReports(id)).thenReturn(Optional.of(directReports));

        Employee result = employeeService.getEmployeeById(id);
        assertEquals(id, result.getId());
        assertEquals(2, result.getDirectReports().size());
        assertEquals(1, result.getProperties().size());
        assertEquals("testdata", result.getProperties().get(0).getKey());
        assertEquals("true", result.getProperties().get(0).getValue());
    }

    @Test
    public void testGetAllTopLevelEmployees() {
        Employee topLevelEmployee = new Employee();
        Long id = 1L;
        topLevelEmployee.setId(id);
        topLevelEmployee.setSupervisorId(null);
        when(employeeRepository.findBySupervisorIdIsNull()).thenReturn(Optional.of(List.of(topLevelEmployee)));
        when(employeeRepository.findById(id)).thenReturn(Optional.of(topLevelEmployee));

        List<Property> properties = new ArrayList<>();
        Property property = new Property();
        property.setEmployeeId(id);
        property.setKey("testdata");
        property.setValue("true");
        properties.add(property);
        when(propertyRepository.findByEmployeeId(anyLong())).thenReturn(Optional.of(properties));

        Employee directReport1 = new Employee();
        directReport1.setId(2L);
        directReport1.setSupervisorId(id);
        Employee directReport2 = new Employee();
        directReport2.setId(3L);
        directReport2.setSupervisorId(id);
        List<Employee> directReports = new ArrayList<>();
        directReports.add(directReport1);
        directReports.add(directReport2);
        when(employeeRepository.findDirectReports(id)).thenReturn(Optional.of(directReports));

        List<Employee> result = employeeService.getAllTopLevelEmployees();
        List<Employee> expectedResult = List.of(topLevelEmployee);

        assertEquals(expectedResult, result);
    }
}