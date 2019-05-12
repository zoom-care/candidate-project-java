package com.zoomcare.candidatechallenge.integrationtests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.web.EmployeeController;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService service;

	@Test
	public void when_getTopLevelEmployees_thenGetTopDog() throws Exception {

		Employee topDog = new Employee();
		topDog.setId(100L);

		Set<Employee> subOrds = new HashSet<Employee>();
		Employee subOrd1 = new Employee();
		subOrd1.setId(110L);
		subOrds.add(subOrd1);

		Employee subOrd2 = new Employee();
		subOrd2.setId(120L);
		subOrds.add(subOrd2);

		topDog.setSubordinates(subOrds);

		List<Employee> topDogList = Arrays.asList(topDog);

		when(service.getTopLevelEmployees()).thenReturn(topDogList);

		mvc.perform(get("/employees/top-level").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", is(100)))
				.andExpect(jsonPath("$[0].subordinates", hasSize(2)));
	}

	@Test
	public void when_getNonExistantEmployee_thenGet404() throws Exception {

		when(service.getEmployeeById(10000L))
				.thenThrow(new EmployeeNotFoundException("Couldn't find an Employee with id: " + 10000L));

		mvc.perform(get("/employees/10000").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void when_getExistingEmployee_thenGetEmployeeDetails() throws Exception {

		Employee bigKahuna = new Employee();
		bigKahuna.setId(100L);
		bigKahuna.addTestProperty("title", "President");
		bigKahuna.addTestProperty("region", "World");

		Employee subOrd1 = new Employee();
		subOrd1.setId(120L);
		subOrd1.setSupervisor(bigKahuna);
		subOrd1.addTestProperty("title", "COO");
		subOrd1.addTestProperty("region", "World");

		when(service.getEmployeeById(120L)).thenReturn(subOrd1);

		mvc.perform(get("/employees/120").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(120))).andExpect(jsonPath("$.properties", hasSize(2)));
	}

}
