package com.zoomcare.candidatechallenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CandidateChallengeApplication.class)
public class CandidateChallengeApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAllEmployees() {
        try {
            mockMvc.perform(get("/api/v1/employee")).andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            System.err.println("Exception thrown in testAllEmployees: " + e.getMessage());
        }
    }

    @Test
    public void testEmployee1() {
        try {
            mockMvc.perform(get("/api/v1/employee/1")).andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            System.err.println("Exception thrown in testAllEmployees: " + e.getMessage());
        }
    }

    @Test
    public void testNonExistentEmployee() {
        try {
            mockMvc.perform(get("/api/v1/employee/-1")).andExpect(status().isNotFound());
        } catch (Exception e) {
            System.err.println("Exception thrown in testAllEmployees: " + e.getMessage());
        }
    }
}
