package com.zoomcare.candidatechallenge.test.unit.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findById() {
        Optional<Employee> employee = this.employeeRepository.findById(1L);

        assertTrue(employee.isPresent());
        assertEquals(new Long(1L), employee.get().getId());
        assertNull(employee.get().getSupervisorId());
    }

    @Test
    public void findByNotExistingId() {
        Optional<Employee> employee = this.employeeRepository.findById(11L);

        assertFalse(employee.isPresent());
    }

    @Test
    public void findBySupervisorIdIsNull() {
        List<Employee> employees = this.employeeRepository.findBySupervisorIdIsNull();

        System.out.println(employees.get(0).getProperties());
        assertEquals(1, employees.size());
        assertEquals("CEO", employees.get(0).getProperties().get("title"));
    }
}
