package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

public class EmployeeServiceImplTest {

    private EmployeeServiceImpl service;
    EmployeeRepository employeeRepository;
    PropertyRepository propertyRepository;

    @Before
    public void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        propertyRepository = Mockito.mock(PropertyRepository.class);
        service = new EmployeeServiceImpl(employeeRepository, propertyRepository);
    }

    @Test
    public void test_getEmployee_success() {
        Optional<Employee> employeeOptional = Optional.of(new Employee());
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(employeeOptional);
        Employee employee = service.getEmployee(5L);
        assertNotNull(employee);
    }

    @Test
    public void test_getEmployees_success() {
        Optional<Employee> employeeOptional = Optional.of(new Employee());
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(employeeOptional);
        List<Employee> employeeResponseList = service.getEmployees();
        assertNotNull(employeeResponseList);
    }
}