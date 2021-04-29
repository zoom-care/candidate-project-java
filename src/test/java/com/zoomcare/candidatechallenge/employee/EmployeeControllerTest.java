package com.zoomcare.candidatechallenge.employee;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findEmployeeByIdTest() throws Exception {
		this.mockMvc.perform(get("/employee?id=2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNotEmpty());

		this.mockMvc.perform(get("/employee?id=99"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void findTopSupervisorsTest() throws Exception {
		this.mockMvc.perform(get("/employee/topSupervisors"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].reports").isNotEmpty());
	}
}
