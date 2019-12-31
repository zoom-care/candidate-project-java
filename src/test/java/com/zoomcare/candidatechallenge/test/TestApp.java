package com.zoomcare.candidatechallenge.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestApp extends CandidateChallengeApplicationTests
{
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testEmployeeCount() throws Exception
	{
		mockMvc.perform(get("/employees/count")).andExpect(status().isOk())
												.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void testSingleEmployee() throws Exception
	{
		mockMvc.perform(get("/employees/4")
					).andDo(print())
				.andExpect(status().isOk())
					 .andExpect(content().contentType("application/json;charset=UTF-8"))
					 .andExpect(jsonPath("$.id").value("4"))
					 .andExpect(jsonPath("$.supervisorId").value("3"))
					 .andExpect(jsonPath("$..value").value("Sales Representative"));
	}
}
