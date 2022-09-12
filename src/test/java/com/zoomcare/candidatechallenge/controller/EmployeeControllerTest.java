package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.output.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void getEmployeeById(){
        Integer id = new Random().nextInt();

        EmployeeDTO employeeDTO = Mockito.mock(EmployeeDTO.class);

        Mockito.when(this.employeeService.getById(id)).thenReturn(employeeDTO);

        try {
            EmployeeDTO returnedEmployeeDTO = this.employeeService.getById(id);
            assertNotNull(returnedEmployeeDTO);
            assertEquals(employeeDTO, returnedEmployeeDTO);
        } finally {
            Mockito.verify(this.employeeService, Mockito.times(1)).getById(id);
        }
    }

    @Test
    public void getTopLevelEmployees(){
        List<EmployeeDTO> employeesDTO = Mockito.mock(List.class);

        Mockito.when(this.employeeService.getAllTopLevelEmployees()).thenReturn(employeesDTO);

        try {
            List<EmployeeDTO> returnedEmployeesDTO = this.employeeService.getAllTopLevelEmployees();
            assertNotNull(returnedEmployeesDTO);
            assertEquals(employeesDTO, returnedEmployeesDTO);
        } finally {
            Mockito.verify(this.employeeService, Mockito.times(1)).getAllTopLevelEmployees();
        }
    }
}
