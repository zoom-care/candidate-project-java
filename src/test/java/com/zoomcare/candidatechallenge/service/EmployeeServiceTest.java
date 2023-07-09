package com.zoomcare.candidatechallenge.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.model.PropertyPK;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    private final Long EMPLOYEE_ID = 2L;
    private final Long SUPERVISOR_ID = 1L;
    private final String PROPERTY_KEY = "key";
    private Employee employee;
    private Employee supervisorEmployee;

    @Mock
    private EmployeeRepository employeeRepository;

    private PropertyPK propertyPK;
    private Property property;
    private ModelMapper modelMapper;

    private EmployeeService employeeService;

    @Before
    public void setUp() {
        modelMapper = new ModelMapper();
        employeeService = new EmployeeService(employeeRepository, modelMapper);

        propertyPK = PropertyPK.builder()
                .employeeId(EMPLOYEE_ID)
                .key(PROPERTY_KEY)
                .build();

        property = Property.builder()
                .id(propertyPK)
                .value("value")
                .build();

        employee = Employee.builder()
                .id(EMPLOYEE_ID)
                .supervisorId(SUPERVISOR_ID)
                .properties(Arrays.asList(property))
                .build();

        supervisorEmployee = Employee.builder()
                .id(1l)
                .directReports(Arrays.asList(employee))
                .build();

    }

    @Test
    public void findByIdFindsEmployeeTest() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));

        Optional<EmployeeDTO> result = employeeService.findById(EMPLOYEE_ID);

        assertTrue(result.isPresent());
        EmployeeDTO employeeDTO = result.get();
        assertEquals(EMPLOYEE_ID, employeeDTO.getId());
        assertEquals(propertyPK.getKey(), employeeDTO.getProperties().get(0).getId().getKey());
        assertEquals(property.getValue(), employeeDTO.getProperties().get(0).getValue());
        assertEquals(employee.getSupervisorId(), employeeDTO.getSupervisorId());
        assertEquals(employee.getProperties().get(0).getValue(), employeeDTO.getProperties().get(0).getValue());
    }

    @Test
    public void findByIdEmployeeNotFoundTest() {
        Optional<EmployeeDTO> result = employeeService.findById(3L);

        assertFalse(result.isPresent());
    }

    @Test
    public void getTopLevelEmployeeFoundTest() {
        when(employeeRepository.findAllBySupervisorIdIsNull()).thenReturn(Arrays.asList(supervisorEmployee));

        List<EmployeeDTO> supervisors = employeeService.getTopLevelEmployee();

        assertFalse(supervisors.isEmpty());
        EmployeeDTO supervisor = supervisors.get(0);
        assertEquals(SUPERVISOR_ID, supervisor.getId());
        assertEquals(supervisorEmployee.getSupervisorId(), supervisor.getSupervisorId());
        assertEquals(supervisorEmployee.getDirectReports().size(), supervisor.getDirectReports().size());
        assertEquals(EMPLOYEE_ID, supervisor.getDirectReports().get(0).getId());
    }

    @Test
    public void getTopLevelEmployeeNotFoundTest() {
        List<EmployeeDTO> supervisors = employeeService.getTopLevelEmployee();

        assertTrue(supervisors.isEmpty());
    }
}