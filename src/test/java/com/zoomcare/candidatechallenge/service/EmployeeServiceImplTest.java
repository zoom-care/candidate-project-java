package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exception.DataNotFoundException;
import com.zoomcare.candidatechallenge.exception.UnexpectedException;
import com.zoomcare.candidatechallenge.model.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.implementation.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void getAllTopLevelEmployees_NonBusinessExceptionThrown_ThrowsUnexpectedException() {

        doThrow(new RuntimeException()).when(employeeRepository).getAllTopLevelEmployees();

        assertThatThrownBy(() -> employeeServiceImpl.getAllTopLevelEmployees()).isInstanceOf(UnexpectedException.class);

        verify(employeeRepository, times(1)).getAllTopLevelEmployees();
    }

    @Test
    public void getAllTopLevelEmployees_BusinessExceptionThrown_ThrowsSuchException() {

        doThrow(new DataNotFoundException()).when(employeeRepository).getAllTopLevelEmployees();

        assertThatThrownBy(() -> employeeServiceImpl.getAllTopLevelEmployees()).isInstanceOf(DataNotFoundException.class);

        verify(employeeRepository, times(1)).getAllTopLevelEmployees();
    }

    @Test
    public void getAllTopLevelEmployees_EmployeesNotFound_ThrowsDataNotFoundException() {

        final List<Employee> mockedEmployees = new ArrayList<>();

        doReturn(mockedEmployees).when(employeeRepository).getAllTopLevelEmployees();

        assertThatThrownBy(() -> employeeServiceImpl.getAllTopLevelEmployees()).isInstanceOf(DataNotFoundException.class);

        verify(employeeRepository, times(1)).getAllTopLevelEmployees();
    }

    @Test
    public void getAllTopLevelEmployees_EmployeesFound_ReturnsSuchEmployees() {

        final List<Employee> mockedEmployees = List.of(new Employee());

        doReturn(mockedEmployees).when(employeeRepository).getAllTopLevelEmployees();

        final List<Employee> employees = employeeServiceImpl.getAllTopLevelEmployees();

        assertThat(employees).isEqualTo(mockedEmployees);

        verify(employeeRepository, times(1)).getAllTopLevelEmployees();
    }

    @Test
    public void findById_NonBusinessExceptionThrown_ThrowsUnexpectedException() {

        final Long id = 1L;

        doThrow(new RuntimeException()).when(employeeRepository).findById(id);

        assertThatThrownBy(() -> employeeServiceImpl.findById(id)).isInstanceOf(UnexpectedException.class);

        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void findById_BusinessExceptionThrown_ThrowsSuchException() {

        final Long id = 1L;

        doThrow(new DataNotFoundException()).when(employeeRepository).findById(id);

        assertThatThrownBy(() -> employeeServiceImpl.findById(id)).isInstanceOf(DataNotFoundException.class);

        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void findById_EmployeeNotFound_ThrowsDataNotFoundException() {

        final Long id = 1L;

        doReturn(Optional.empty()).when(employeeRepository).findById(id);

        assertThatThrownBy(() -> employeeServiceImpl.findById(id)).isInstanceOf(DataNotFoundException.class);

        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void findById_EmployeeFound_ReturnsSuchEmployee() {

        final Long id = 1L;

        final Employee mockedEmployee = new Employee();

        doReturn(Optional.of(mockedEmployee)).when(employeeRepository).findById(id);

        final Employee employee = employeeServiceImpl.findById(id);

        assertThat(employee).isEqualTo(mockedEmployee);

        verify(employeeRepository, times(1)).findById(id);
    }
}
