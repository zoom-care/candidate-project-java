package com.example.zoomcarecodechallenge;

import com.example.zoomcarecodechallenge.dto.EmployeeDTO;
import com.example.zoomcarecodechallenge.dto.PropertiesDTO;
import com.example.zoomcarecodechallenge.exceptions.EmployeeNotFoundException;
import com.example.zoomcarecodechallenge.model.Employee;
import com.example.zoomcarecodechallenge.model.Properties;
import com.example.zoomcarecodechallenge.repository.EmployeeRepository;
import com.example.zoomcarecodechallenge.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void testGetTopLevelEmployees() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setSupervisor(null);
        employee1.setReports(Arrays.asList(new Employee(), new Employee()));

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setSupervisor(null);
        employee2.setReports(Arrays.asList(new Employee()));

        when(employeeRepository.findBySupervisorIsNull())
                .thenReturn(Arrays.asList(employee1, employee2));

        List<EmployeeDTO> topLevelEmployees = employeeService.getTopLevelEmployees();

        assertEquals(2, topLevelEmployees.size());
        assertEquals(1L, topLevelEmployees.get(0).getId());
        assertEquals(2L, topLevelEmployees.get(1).getId());
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setSupervisor(new Employee());
        employee.setReports(new ArrayList<>());
        employee.setProperties(Arrays.asList(new Properties(), new Properties()));

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeDTO employeeDTO = employeeService.getEmployee(1L);

        assertEquals(1L, employeeDTO.getId());
        assertEquals(2, employeeDTO.getProperties().size());
    }

    @Test
    public void testGetEmployeeNotFound() {
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(1L));
    }

    @Test
    public void testConvertToDTO() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setSupervisor(new Employee());
        employee.setReports(new ArrayList<>());
        employee.setProperties(Arrays.asList(new Properties(), new Properties()));

        EmployeeDTO employeeDTO = employeeService.convertToDTO(employee);

        assertEquals(1L, employeeDTO.getId());
        assertEquals(2, employeeDTO.getProperties().size());
    }

    @Test
    public void testConvertPropertiesToDTO() {
        Properties property1 = new Properties();
        property1.setKey("key1");
        property1.setValue("value1");

        Properties property2 = new Properties();
        property2.setKey("key2");
        property2.setValue("value2");

        List<Properties> properties = Arrays.asList(property1, property2);

        List<PropertiesDTO> propertiesDTOs = employeeService.convertPropertiesToDTO(properties);

        assertEquals(2, propertiesDTOs.size());
        assertEquals("key1", propertiesDTOs.get(0).getKey());
        assertEquals("value2", propertiesDTOs.get(1).getValue());
    }

    @Test
    public void testConvertToDTOWithNullSupervisor() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setSupervisor(null);
        employee.setReports(Arrays.asList(new Employee(), new Employee()));
        employee.setProperties(Arrays.asList(new Properties(), new Properties()));

        EmployeeDTO employeeDTO = employeeService.convertToDTO(employee);

        assertEquals(1L, employeeDTO.getId());
        assertEquals(2, employeeDTO.getProperties().size());
        assertEquals(2, employeeDTO.getReports().size());}
}

