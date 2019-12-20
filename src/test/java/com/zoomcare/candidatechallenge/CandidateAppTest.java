package com.zoomcare.candidatechallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CandidateAppTest {

	@Inject
	private TestRestTemplate restTemplate;
	
	@Test
	public void testGetSpecificEmployee7() {
		HttpEntity<Void> httpEntity = new HttpEntity<>(null, new HttpHeaders());
		ResponseEntity<EmployeeDto> result = restTemplate.exchange("/rest/employee/id/7", HttpMethod.GET, httpEntity, EmployeeDto.class );
		assertEquals(HttpStatus.OK, result.getStatusCode());
		EmployeeDto employee = result.getBody();
		assertEquals(7, employee.getId());
		assertEquals(1, employee.getProperties().size());
		assertEquals(2, employee.getDirectorReport().size());
		assertEquals("Vice President of Marketing",  employee.getProperties().get("7"));
		
	}
	
	@Test
	public void testGetSpecificEmployee2() {
		HttpEntity<Void> httpEntity = new HttpEntity<>(null, new HttpHeaders());
		ResponseEntity<EmployeeDto> result = restTemplate.exchange("/rest/employee/id/2", HttpMethod.GET, httpEntity, EmployeeDto.class );
		assertEquals(HttpStatus.OK, result.getStatusCode());
		EmployeeDto employee = result.getBody();
		assertEquals(2, employee.getId());
		assertEquals(1, employee.getProperties().size());
		assertEquals(2, employee.getDirectorReport().size());
		assertEquals("Vice President of Sales", employee.getProperties().get("2"));
	}
	
	@Test
	public void testGetTopLevelList() {
		HttpEntity<Void> httpEntity = new HttpEntity<>(null, new HttpHeaders());
		ResponseEntity<EmployeeDto[]> result = restTemplate.exchange("/rest/employee/top-level", HttpMethod.GET, httpEntity, EmployeeDto[].class );
		assertEquals(HttpStatus.OK, result.getStatusCode());
		EmployeeDto[] employees = result.getBody();
		assertEquals(1, employees.length);
		EmployeeDto employee = employees[0];
		assertEquals(1, employee.getId());
		assertEquals(1, employee.getProperties().size());
		assertEquals(3, employee.getDirectorReport().size());
		assertEquals("CEO", employee.getProperties().get("1"));
	}
	
}
