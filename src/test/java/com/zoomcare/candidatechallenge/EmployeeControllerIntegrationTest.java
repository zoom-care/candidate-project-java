package com.zoomcare.candidatechallenge;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerIntegrationTest {

	@Autowired
	private EmployeeControler employeeController;

	@Test
	public void whenFindById_5() {
		Employee employee = employeeController.getEmployeeById(5L);
		assertEquals("Employee ID is 1", 5L, employee.getEmployeeId());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenFindById_Invalid() {
		employeeController.getEmployeeById(0L);
	}

	@Test
	public void whenFindById_Null() {
		Employee[] employees = employeeController.getEmployees(null);
		assertEquals("CEO Employee ID is 1", 1L, employees[0].getEmployeeId());
	}
	
}
