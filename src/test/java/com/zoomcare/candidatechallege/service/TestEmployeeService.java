package com.zoomcare.candidatechallege.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.CandidateChallengeApplication;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = CandidateChallengeApplication.class)
public class TestEmployeeService {

	@Autowired
	EmployeeService service;

	@Test
	public void testGetTopLevelEmployees() {
		List<Employee> employees = service.getTopLevelEmployees();
		Assert.assertFalse(employees.isEmpty());
		employees.forEach(e -> Assert.assertNull(e.getSupervisorId()));
	}

	@Test
	public void testGetEmployee_givenValidId_thenReturnEmployeeWithMatchingID() {
		Long id = 2L;
		Employee employee = service.getEmployeeById(id);
		Assert.assertTrue(employee.getId() == id);
	}

	@Test
	public void testGetEmployee_givenInvalidId_thenCatchEmployeeNotFoundExeption() {
		boolean exceptionCaught = false;
		try {
			service.getEmployeeById(1000L);
		} catch (EmployeeNotFoundException ex) {
			exceptionCaught = true;
		}
		Assert.assertTrue(exceptionCaught == true);
	}
}
