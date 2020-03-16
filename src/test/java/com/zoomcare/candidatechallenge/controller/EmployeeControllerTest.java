package com.zoomcare.candidatechallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void findEmployeeById() throws Exception {
        final Employee employee = getEmployeeFromJson();
        given(this.employeeRepository.findById(7L)).willReturn(Optional.of(employee));
        this.mvc.perform(get("/api/employees/{id}", 7))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json("{\"id\":7,\"employees\":[{\"id\":8,\"employees\":[],\"properties\":[{\"employeeId\":8,\"key\":\"region\",\"value\":\"North America\"},{\"employeeId\":8,\"key\":\"title\",\"value\":\"Regional Director of Marketing\"}]},{\"id\":9,\"employees\":[],\"properties\":[{\"employeeId\":9,\"key\":\"region\",\"value\":\"Europe\"},{\"employeeId\":9,\"key\":\"title\",\"value\":\"Regional Director of Marketing\"}]}],\"properties\":[{\"employeeId\":7,\"key\":\"title\",\"value\":\"Vice President of Marketing\"}]}"));
    }

    @Test
    public void findAllTopLevelEmployees() throws Exception {
        final Employee employee = getEmployeeFromJson();
        given(this.employeeRepository.findAllTopLevelEmployees()).willReturn(Collections.singletonList(employee));
        this.mvc.perform(get("/api/employees/top-level"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json("[{\"id\":7,\"employees\":[{\"id\":8,\"employees\":[],\"properties\":[{\"employeeId\":8,\"key\":\"region\",\"value\":\"North America\"},{\"employeeId\":8,\"key\":\"title\",\"value\":\"Regional Director of Marketing\"}]},{\"id\":9,\"employees\":[],\"properties\":[{\"employeeId\":9,\"key\":\"region\",\"value\":\"Europe\"},{\"employeeId\":9,\"key\":\"title\",\"value\":\"Regional Director of Marketing\"}]}],\"properties\":[{\"employeeId\":7,\"key\":\"title\",\"value\":\"Vice President of Marketing\"}]}]"));
    }

    private Employee getEmployeeFromJson() throws java.io.IOException {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
        final ObjectMapper objectMapper = new ObjectMapper();
        final EmployeeDTO employeeDTO = objectMapper.readValue(
            "{\"id\":7,\"employees\":[{\"id\":8,\"employees\":[],\"properties\":[{\"employeeId\":8,\"key\":\"region\",\"value\":\"North America\"},{\"employeeId\":8,\"key\":\"title\",\"value\":\"Regional Director of Marketing\"}]},{\"id\":9,\"employees\":[],\"properties\":[{\"employeeId\":9,\"key\":\"region\",\"value\":\"Europe\"},{\"employeeId\":9,\"key\":\"title\",\"value\":\"Regional Director of Marketing\"}]}],\"properties\":[{\"employeeId\":7,\"key\":\"title\",\"value\":\"Vice President of Marketing\"}]}",
            EmployeeDTO.class);
        return modelMapper.map(employeeDTO, Employee.class);
    }
}
