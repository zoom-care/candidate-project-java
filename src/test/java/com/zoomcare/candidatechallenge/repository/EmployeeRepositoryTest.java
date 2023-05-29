package com.zoomcare.candidatechallenge.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.dao.EmployeeDAO;
import com.zoomcare.candidatechallenge.exception.EmployeeException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Properties;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {
	
	@Mock
	private EmployeeDAO employeeDAO;
	
	@InjectMocks
	private EmployeeRepository employeeRepository;
	
	// POSITIVE TEST
	
	@Test
	public void givenValidIdThenshouldReturnEmployeeInformationWithSupervisor() throws EmployeeException {
		Optional<Employee> employeeOptional = Optional.of(new Employee(2l, 1l, Arrays.asList(
				new Properties("region", "North America"),
				new Properties("title", "Regional Director of Sales"))));
		
		when(employeeDAO.findById(any())).thenReturn(employeeOptional);
		
		Employee employee = employeeRepository.getEmployeeById(2l);
		
		assertNotNull(employee);
		assertNotNull(employee.getSupervisorId());
	}
	
	@Test
	public void givenValidIdThenshouldReturnEmployeeInformationWithoutSupervisor() throws EmployeeException {
		Optional<Employee> employeeOptional = Optional.of(new Employee(1l, null, Arrays.asList(
				new Properties("title", "CEO"))));
		
		when(employeeDAO.findById(any())).thenReturn(employeeOptional);
		
		Employee employee = employeeRepository.getEmployeeById(2l);
		
		assertNotNull(employee);
		assertNull(employee.getSupervisorId());
	}
	
	@Test
	public void withExistingEmployeesThenShoulReturnEmployeesList() {
		List<Employee> employeeList = Arrays.asList(
				new Employee(1l, null, Arrays.asList(
						new Properties("title", "CEO"))),
				new Employee(2l, 1l, Arrays.asList(
						new Properties("region", "North America"),
						new Properties("title", "Regional Director of Sales")))
		);
		
		when(employeeDAO.findAll()).thenReturn((Iterable<Employee>)employeeList);
		
		Iterable<Employee> employeeIterable = employeeRepository.getAllEmployees();
		
		assertNotNull(employeeIterable);
		assertTrue(employeeIterable.iterator().hasNext());
	}
	
	// NEGATIVE TEST
	
	@Test
	public void givenNotExistingIdThenShouldReturnNull() throws EmployeeException {
		when(employeeDAO.findById(any())).thenReturn(Optional.empty());
		Employee employee = employeeRepository.getEmployeeById(1l);
		assertNull(employee);
	}
	
	@Test
	public void givenNullIdThenShouldReturnException() {
		
		ThrowingCallable fail = () -> {
			employeeRepository.getEmployeeById(null);
		};
		
		assertThatCode(fail).isInstanceOf(EmployeeException.class);
	}
	
}
