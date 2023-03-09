package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EntityPropertyPk;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class OrganizationServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private OrganizationService organizationService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        organizationService = new OrganizationService(employeeRepository);
    }

    @Test
    public void testFindById(){
        Long employee_id = 99L;
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(generateEmployee(employee_id)));

        Optional<EmployeeDTO> result = organizationService.findById(employee_id);
        assertTrue(result.isPresent());
        assertFalse(result.get().getProperties().isEmpty());
        assertEquals(2,result.get().getProperties().size());
        assertEquals("KEY", result.get().getProperties().get(0).getKey());
        assertEquals("KEY2", result.get().getProperties().get(1).getKey());
        assertFalse(result.get().getEmployees().isEmpty());
        assertEquals(1, result.get().getEmployees().size());
    }

    @Test
    public void testGetAllTopLevelsEmployees(){
        List<Employee> employees = Arrays.asList(
                generateEmployee(55L),
                generateEmployee(66L),
                generateEmployee(77L));
        when(employeeRepository.findAllBySupervisorIdIsNull()).thenReturn(employees);

        List<EmployeeDTO> employeeDTOs= organizationService.getAllTopLevelsEmployees();

        assertFalse(employeeDTOs.isEmpty());
        assertEquals(3, employeeDTOs.size() );
        assertEquals(55L, employeeDTOs.get(0).getId().longValue() );
        assertEquals(66L, employeeDTOs.get(1).getId().longValue() );
        assertEquals(77L, employeeDTOs.get(2).getId().longValue() );
    }

    private Employee generateEmployee(Long employee_id){
        Employee employee = new Employee();
        employee.setId(employee_id);
        List<Property> properties = new ArrayList<>();
        Property property = new Property();
        EntityPropertyPk pk = new EntityPropertyPk();
        pk.setKey("KEY");
        pk.setEmployee(employee);
        property.setId(pk);
        property.setValue("VALUE");
        properties.add(property);

        Property property2 = new Property();
        EntityPropertyPk pk2 = new EntityPropertyPk();
        pk2.setKey("KEY2");
        pk2.setEmployee(employee);
        property2.setId(pk2);
        property2.setValue("VALUE2");
        properties.add(property2);

        employee.setProperties(properties);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employee.setEmployees(employees);

        return employee;
    }
}