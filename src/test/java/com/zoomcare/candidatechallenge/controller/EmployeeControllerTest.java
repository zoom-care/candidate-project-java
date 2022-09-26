package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.DataNotFoundException;
import com.zoomcare.candidatechallenge.model.entity.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTopLevelEmployees_EmployeesFound_ReturnsStatusOk() throws Exception {
        doReturn(new ArrayList<>()).when(employeeService).getAllTopLevelEmployees();

        mockMvc.perform(get("/employees/top-level")).andExpect(status().isOk());

        verify(employeeService, times(1)).getAllTopLevelEmployees();
    }

    @Test
    void getAllTopLevelEmployees_EmployeesNotFound_ReturnsStatusNotFound() throws Exception {
        doThrow(new DataNotFoundException()).when(employeeService).getAllTopLevelEmployees();

        mockMvc.perform(get("/employees/top-level")).andExpect(status().isNotFound());

        verify(employeeService, times(1)).getAllTopLevelEmployees();
    }

    @Test
    void findById_EmployeeFound_ReturnsStatusOk() throws Exception {

        final Long id = 1L;

        doReturn(new Employee()).when(employeeService).findById(id);

        mockMvc.perform(get("/employees/" + id)).andExpect(status().isOk());

        verify(employeeService, times(1)).findById(id);
    }

    @Test
    void findById_EmployeeNotFound_ReturnsStatusNotFound() throws Exception {

        final Long id = 1L;

        doThrow(new DataNotFoundException()).when(employeeService).findById(id);

        mockMvc.perform(get("/employees/" + id)).andExpect(status().isNotFound());

        verify(employeeService, times(1)).findById(id);
    }
}
