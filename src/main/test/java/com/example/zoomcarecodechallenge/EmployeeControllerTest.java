package com.example.zoomcarecodechallenge;

import com.example.zoomcarecodechallenge.controller.EmployeeController;
import com.example.zoomcarecodechallenge.dto.EmployeeDTO;
import com.example.zoomcarecodechallenge.dto.PropertiesDTO;
import com.example.zoomcarecodechallenge.model.Employee;
import com.example.zoomcarecodechallenge.model.Properties;
import com.example.zoomcarecodechallenge.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.example.zoomcarecodechallenge.dto.EmployeeDTO;
import com.example.zoomcarecodechallenge.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetTopLevelEmployees() {
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId(2L);

        List<EmployeeDTO> expectedEmployees = Arrays.asList(employeeDTO1, employeeDTO2);

        when(employeeService.getTopLevelEmployees())
                .thenReturn(expectedEmployees);

        ResponseEntity<List<EmployeeDTO>> responseEntity = employeeController.getTopLevelEmployees();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEmployees, responseEntity.getBody());
    }

    @Test
    public void testGetEmployee() {
        EmployeeDTO expectedEmployee = new EmployeeDTO();
        expectedEmployee.setId(1L);

        when(employeeService.getEmployee(1L))
                .thenReturn(expectedEmployee);

        ResponseEntity<EmployeeDTO> responseEntity = employeeController.getEmployee(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEmployee, responseEntity.getBody());
    }
}
