package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.service.OrganizationService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
public class OrganizationControllerTest extends TestCase {

    @MockBean
    private OrganizationService organizationService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllTopLevelsEmployees() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/organization/top"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/organization/2"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}