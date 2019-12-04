package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.data.model.Employee;
import com.zoomcare.candidatechallenge.data.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataJpaTest
public class RetrieveTopLevelUserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void whenFindByEmployeeId_thenReturnEmployee()
    {
        Employee employee = employeeRepository.findEmployeeById(2L);

        Assert.assertEquals(employee.getId().longValue(), 2L);
    }
}