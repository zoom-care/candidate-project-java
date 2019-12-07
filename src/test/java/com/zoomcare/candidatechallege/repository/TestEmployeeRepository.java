package com.zoomcare.candidatechallege.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.CandidateChallengeApplication;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = CandidateChallengeApplication.class)
public class TestEmployeeRepository {

	@Autowired
	EmployeeRepository repository;

	@Test
	public void testFindBySupervisorIdIsNull() {

		List<Employee> employees = repository.findBySupervisorIdIsNull();
		Assert.assertFalse(employees.isEmpty());

		employees.forEach(e -> Assert.assertNull(e.getSupervisorId()));

	}
}
