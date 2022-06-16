package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.CandidateChallengeApplication;
import com.zoomcare.candidatechallenge.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CandidateChallengeApplication.class})
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void insertAndFindAll() {
        int size = employeeRepository.findAll().size();

        Employee employee = new Employee();
        employee.addProperty("key", "value");
        employee.addProperty("key2", "value2");
        employee.addProperty("key3", "value3");

        employeeRepository.save(employee);
        List<Employee> employees = employeeRepository.findAll();

        Assert.assertEquals(size + 1 , employees.size());
    }

    @Test
    @Transactional
    public void insertAndFind() {
        Employee employee = new Employee();
        employee.addProperty("key", "value");
        employee.addProperty("key2", "value2");
        employee.addProperty("key3", "value3");

        Employee employee1 = employeeRepository.save(employee);
        Optional<Employee> employeeSaved = employeeRepository.findById(employee1.getId());

        Assert.assertTrue(employeeSaved.isPresent());
        Assert.assertEquals(employee.getSupervisor() , employeeSaved.get().getSupervisor());
        Assert.assertEquals(employee.getProperties() , employeeSaved.get().getProperties());
    }

}
