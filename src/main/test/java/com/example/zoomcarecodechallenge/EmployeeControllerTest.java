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

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeController = new EmployeeController(employeeRepository);
    }


    @Test
    public void testGetEmployeeFound() {
        // Arrange
        EmployeeDTO employeeD = new EmployeeDTO(1L);
        employeeD.setProperties(List.of(new PropertiesDTO("key1", "value1")));
        Employee employee = new Employee(1L);
        employee.getProperties().add(new Properties("key1", "value1"));

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        EmployeeDTO result = employeeController.getEmployee(1L);

        // Assert
         assertThat(result.getProperties()).hasSize(1);
        assertThat(result.getReports()).isEmpty();
    }

}
