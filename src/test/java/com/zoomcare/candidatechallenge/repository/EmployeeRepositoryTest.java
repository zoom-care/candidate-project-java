package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    void getAllTopLevelEmployees_NonExistentTopLevelEmployees_ReturnsEmptyList() {
        final Employee supervisor = new Employee();
        supervisor.setId(2L);

        employeeRepository.findById(1L).get().setSupervisor(supervisor);

        List<Employee> employees = employeeRepository.getAllTopLevelEmployees();

        assertThat(employees.isEmpty()).isTrue();
    }


    @Test
    void getAllTopLevelEmployees_ExistentTopLevelEmployees_ReturnsSuchEmployees() {
        List<Employee> employees = employeeRepository.getAllTopLevelEmployees();

        assertThat(employees.size()).isEqualTo(1);
    }

    @Test
    void findById_ExistentId_EmployeeIsPresent() {
        Optional<Employee> employee = employeeRepository.findById(1L);
        assertThat(employee.isPresent()).isTrue();
    }

    @Test
    void findById_NonExistentId_EmployeeIsNotPresent() {
        Optional<Employee> employee = employeeRepository.findById(100L);
        assertThat(employee.isPresent()).isFalse();
    }
}
