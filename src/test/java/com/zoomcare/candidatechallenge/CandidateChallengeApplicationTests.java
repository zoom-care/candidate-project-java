package com.zoomcare.candidatechallenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.web.EmployeeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateChallengeApplicationTests {
	
	@Autowired
	private EmployeeController controller;
	
	@Test
	public void contextLoads() {
		 				assertThat(controller).isNotNull();
			
	}

}
