package com.zoomcare.candidatechallenge.service;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.dto.EmployeeInfoDTO;
import com.zoomcare.candidatechallenge.dto.TopLevelListDTO;
import com.zoomcare.candidatechallenge.exception.EmployeeException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Properties;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	// POSITIVE TEST
	
	@Test
	public void givenValidIdThenshouldReturnEmployeeInformationWithSupervisor() throws EmployeeException {
		Employee employeeResult = new Employee(2l, 1l, Arrays.asList(
				new Properties("region", "North America"),
				new Properties("title", "Regional Director of Sales")));
		
		when(employeeRepository.getEmployeeById(any())).thenReturn(employeeResult);
		EmployeeInfoDTO employeeInfo = employeeService.getEmployeeInformation(2l);
		
		assertNotNull(employeeInfo);
		assertNotNull(employeeInfo.getSupervisor());
	}
	
	@Test
	public void givenValidIdThenshouldReturnEmployeeInformationWithoutSupervisor() throws EmployeeException {
		Employee employeeResult = new Employee(1l, null, Arrays.asList(
				new Properties("title", "CEO")));
		
		when(employeeRepository.getEmployeeById(any())).thenReturn(employeeResult);
		EmployeeInfoDTO employeeInfo = employeeService.getEmployeeInformation(1l);
		
		assertNotNull(employeeInfo);
		assertNull(employeeInfo.getSupervisor());
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
		
		when(employeeRepository.getAllEmployees()).thenReturn((Iterable<Employee>)employeeList);
		
		List<TopLevelListDTO> topLevelList = employeeService.getAllEmployees();
		
		assertNotNull(topLevelList);
		assertEquals(topLevelList.size(), 1);
		assertNotNull(topLevelList.get(0).getUnderling());
		assertEquals(topLevelList.get(0).getUnderling().size(), 1);
	}
	
	// NEGATIVE TEST
	
	@Test
	public void givenNotExistingIdThenShouldReturnException() throws EmployeeException {
		when(employeeRepository.getEmployeeById(any())).thenReturn(null);
		ThrowingCallable fail = () -> {
			employeeService.getEmployeeInformation(20l);
		};
		
		assertThatCode(fail).isInstanceOf(EmployeeException.class);
	}
	
	@Test
	public void givenNullIdThenShouldReturnException() {
		
		ThrowingCallable fail = () -> {
			employeeService.getEmployeeInformation(null);
		};
		
		assertThatCode(fail).isInstanceOf(EmployeeException.class);
	}
}
