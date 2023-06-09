package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeResponse;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Property> propertyList = new ArrayList<>();
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(employeeOptional);
        Mockito.when(propertyRepository.findAllPropertyByEmployeeId(Mockito.any())).thenReturn(propertyList);
        EmployeeResponse employee = service.getEmployee(5L);
        System.out.println(employee);
    }
}