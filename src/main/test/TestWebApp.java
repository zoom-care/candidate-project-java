package test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.zoomcare.candidatechallenge.models.Employee;

public class TestWebApp extends CandidateChallengeTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testEmployeeById() throws Exception {
		mockMvc.perform(get("/employee/5")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value("5")).andExpect(jsonPath("$.properties").value("[\r\n"
						+ "            {\r\n"
						+ "                \"value\": \"Europe\",\r\n"
						+ "                \"key\": \"region\"\r\n"
						+ "            },\r\n"
						+ "            {\r\n"
						+ "                \"value\": \"Regional Director of Sales\",\r\n"
						+ "                \"key\": \"title\"\r\n"
						+ "            }\r\n"
						+ "        ]"))
				.andExpect(jsonPath("$.bySupervisorList").value("null"));

	}
	
	@Test
	public void testTopLevelEmployees() throws Exception {
		mockMvc.perform(get("/employee/employees")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.properties").value("[\r\n"
						+ "            {\r\n"
						+ "                \"value\": \"CEO\",\r\n"
						+ "                \"key\": \"title\"\r\n"
						+ "            }\r\n"
						+ "        ]"))
				.andExpect(jsonPath("$.bySupervisorList").value("[\r\n"
						+ "            [\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"1\",\r\n"
						+ "                    \"employee_id\": \"2\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"2\",\r\n"
						+ "                    \"employee_id\": \"3\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"3\",\r\n"
						+ "                    \"employee_id\": \"4\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"2\",\r\n"
						+ "                    \"employee_id\": \"5\"\r\n"
						+ "                }\r\n"
						+ "            ],\r\n"
						+ "            [\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"1\",\r\n"
						+ "                    \"employee_id\": \"6\"\r\n"
						+ "                }\r\n"
						+ "            ],\r\n"
						+ "            [\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"1\",\r\n"
						+ "                    \"employee_id\": \"7\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"7\",\r\n"
						+ "                    \"employee_id\": \"8\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"supervisor_id\": \"7\",\r\n"
						+ "                    \"employee_id\": \"9\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        ]"));

	}

}