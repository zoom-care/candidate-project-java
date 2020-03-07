package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exception.UserDoesNotExistException;
import com.zoomcare.candidatechallenge.model.db.Employee;
import com.zoomcare.candidatechallenge.model.db.Properties;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PropertiesService propertiesService;

    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setup() {
        List<Properties> propertiesList = new ArrayList<>();
        BigInteger bigInteger = new BigInteger("1");
        Properties properties = new Properties(bigInteger, "test_key", "test_value");
        propertiesList.add(properties);

        Employee employee = new Employee();
        employee.setId(new BigInteger("1"));
        employee.setSupervisorId(new BigInteger("2"));

        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.putIfAbsent("test_key", "test_value");

        Mockito.when(propertiesService.getPropertiesByEmployeeId(ArgumentMatchers.any(BigInteger.class)))
                .thenReturn(propertiesMap);

        Mockito.when(employeeRepository.findById(ArgumentMatchers.any(BigInteger.class)))
                .thenReturn(Optional.of(employee));
    }

    @Test
    public void testGetSupervisor() throws UserDoesNotExistException {
        Employee employee = new Employee();
        employee.setSupervisorId(new BigInteger("1"));
        employee.setId(new BigInteger("1"));
        List<Employee> employees = employeeService.getSupervisor(employee);
        Assert.assertEquals(1, employees.size());
    }

    @Test
    public void testGetSupervisorWithNull() throws UserDoesNotExistException {
        Employee employee = new Employee();
        employee.setSupervisorId(null);
        employee.setId(new BigInteger("1"));
        List<Employee> employees = employeeService.getSupervisor(employee);
        Assert.assertEquals(0, employees.size());
    }
}
