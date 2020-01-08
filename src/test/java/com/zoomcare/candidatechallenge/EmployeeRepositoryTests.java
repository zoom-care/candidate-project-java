package com.zoomcare.candidatechallenge;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.domain.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private EmployeeRepository eRepository;
	
	
	 @Test
	 public void should_find_all_employees() {

	        Iterable<Employee> employees = eRepository.findAll();

	        assertThat(employees).isNotEmpty();
	    }
	

}
