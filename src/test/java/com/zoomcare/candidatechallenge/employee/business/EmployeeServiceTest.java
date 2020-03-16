package com.zoomcare.candidatechallenge.employee.business;

import com.zoomcare.candidatechallenge.employee.dataaccess.EmployeeRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepositoryMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    void getEmployees() {

    }

    @Test
    void getEmployee() {
    }
}