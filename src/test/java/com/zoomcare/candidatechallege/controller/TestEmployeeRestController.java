package com.zoomcare.candidatechallege.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zoomcare.candidatechallenge.CandidateChallengeApplication;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CandidateChallengeApplication.class) // Provides a web application context
public class TestEmployeeRestController {

	final Logger logger = LogManager.getLogger(TestEmployeeRestController.class.getSimpleName());

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@Before
	public void setUp() {
		logger.info("TestEmployeeRestController SETUP");
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// Only checking response status in these tests below.
	// More tests can be added to check/validate the response body so we can
	// increase coverage

	@Test
	public void testGetTopLevelEmployees() {
		try {
			mvc.perform(get("/api/employees")).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error("testGetTopLevelEmployees failed: {}",e);
		}
	}

	@Test
	public void testGetEmployee_givenValidId_thenStatusIsOk() {
		try {
			mvc.perform(get("/api/employees/1")).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error("testGetEmployee_givenValidId_thenStatusIsOk failed: {}",e);
		}
	}

	@Test
	public void testGetEmployee_givenInvalidId_thenStatusIsNotFound() {
		try {
			mvc.perform(get("/api/employees/9999")).andExpect(status().isNotFound());
		} catch (Exception e) {
			logger.error("testGetEmployee_givenInvalidId_thenStatusIsNotFound failed: {}",e);
		}
	}
}
