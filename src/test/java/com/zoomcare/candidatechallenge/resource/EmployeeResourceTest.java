package com.zoomcare.candidatechallenge.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeResourceTest {
	
	@Autowired
	private MockMvc webTestClient;
	
	@Test
	public void givenValidIdThenshouldReturnEmployeeInformationWithoutSupervisor() throws Exception {
		
		webTestClient.perform(MockMvcRequestBuilders
				.get("/v1/employee/1")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))			
			.andDo(MockMvcResultHandlers.print());;	
	}
	
	@Test
	public void givenValidIdThenshouldReturnEmployeeInformationWithSupervisor() throws Exception {
		
		webTestClient.perform(MockMvcRequestBuilders
				.get("/v1/employee/2")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
			.andDo(MockMvcResultHandlers.print());;	
	}
	
	@Test
	public void givenInvalidIdThenshouldReturnNotFoundMessage() throws Exception {
		
		webTestClient.perform(MockMvcRequestBuilders
				.get("/v1/employee/100")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound())
			.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Employee ID 100 not found"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void withExistingEmployeesThenShoulReturnEmployeesList() throws Exception {
		webTestClient.perform(MockMvcRequestBuilders
				.get("/v1/employee/all")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}

}
