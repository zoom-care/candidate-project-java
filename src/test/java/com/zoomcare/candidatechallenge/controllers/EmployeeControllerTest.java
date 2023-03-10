package com.zoomcare.candidatechallenge.controllers;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.service.EmployeeServiceImpl;

@RunWith( SpringRunner.class )
@SpringBootTest
@ContextConfiguration
public class EmployeeControllerTest {
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
 
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

	@Test
	public void testFindById() {
        Optional<Employee> opt = Optional.of(getEmployee());
		doReturn(opt).when(employeeRepository).findById(2L);
		Employee employee = employeeService.findEmployeeById(2L);
		assertEquals(employee.getId().intValue(), 10);  
	}

	@Test
	public void testFindByIdNegative() {
        Optional<Employee> opt = Optional.of(getEmployee());
		doReturn(opt).when(employeeRepository).findById(2L);
		Employee employee = employeeService.findEmployeeById(2L);
		assertNotEquals(employee.getId().intValue(), 1);  
	}

	@Test
	public void testFindBySupervisorId() {
        List<Employee> empls = new ArrayList<Employee>();
        empls.add(getEmployee());
		doReturn(empls).when(employeeRepository).findBySupervisorId(1L);
		List<Employee> employees = employeeService.findBySupervisorId(1L);
		assertTrue(employees.size() > 0);  
	}

	@Test
	public void testFindBySupervisorIdNegative() {
        List<Employee> empls = new ArrayList<Employee>();
        empls.add(getEmployee());
		doReturn(empls).when(employeeRepository).findBySupervisorId(1L);
		List<Employee> employees = employeeService.findBySupervisorId(2L);
		assertTrue(employees.isEmpty());  
	}

	@Test
	public void testSaveEmployee() {
        Employee emp = getEmployee();
        Employee emp1 = getEmployee();
		doReturn(emp).when(employeeRepository).save(emp1);
		Employee savedEmp = employeeService.save(emp1);
		assertEquals(savedEmp.getId().intValue(), 10);  
	}
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setId(10L);
		employee.setSupervisorId(1L);
		Property property = new Property();
		property.setKey("title");
		property.setValue("Regional Director of Sales");
		List<Property> properties = new ArrayList<Property>();
		properties.add(property);
		employee.setProperties(properties);
		return employee;
	}

}
