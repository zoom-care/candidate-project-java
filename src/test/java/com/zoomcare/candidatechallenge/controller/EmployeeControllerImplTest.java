package com.zoomcare.candidatechallenge.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
public class EmployeeControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup(){

    }

    @Nested
    @DisplayName("GET /employee")
    class ShouldThrowNotFoundExceptionWhenEmployeeDoesNotExist {
        @Test
        @DisplayName("Should throw a not found exception when an employee does not exist in the database")
        void shouldThrowANotFoundException() throws Exception {
            mvc.perform(get("/api/v1/employee/55")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect( result ->
                            assertEquals(404,result.getResponse().getStatus()));
        }

    }
}
