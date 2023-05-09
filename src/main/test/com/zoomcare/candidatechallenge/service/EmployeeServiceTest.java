package com.zoomcare.candidatechallenge.service;


import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import com.zoomcare.candidatechallenge.model.EmployeeModel;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    private EmployeeService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        service = new EmployeeService(repository);
    }

    @Test
    public void testTopEmployees_whenTopRequired_thenReturnTop(){

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(Long.valueOf(1));
        employeeEntity.setSupervisor(null);
        List<EmployeeEntity> entities = new ArrayList<>();
        entities.add(employeeEntity);

        when(repository.findBySupervisorIsNull()).thenReturn(entities);
        List<EmployeeModel> topEmployees = service.getTopEmployees();
        assertEquals(1,topEmployees.size());
        assertEquals(EmployeeModel.parseFrom(employeeEntity),topEmployees.get(0));
    }

    @Test
    public void testEmployee_whenEmployeeIdIsGiven_thenEmployeeReturned(){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(Long.valueOf(5));

        when(repository.findById(Long.valueOf(5))).thenReturn(Optional.of(employeeEntity));
        EmployeeModel employeeModel = service.getEmployee(Long.valueOf(5));

        assertNotNull(employeeModel);
        assertEquals(employeeEntity.getId(),employeeModel.getId());
        assertEquals(EmployeeModel.parseFrom(employeeEntity),employeeModel);
    }
}
