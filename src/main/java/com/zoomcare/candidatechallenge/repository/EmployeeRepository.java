package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, BigInteger> {

    List<Employee> findEmployeesBySupervisorId(BigInteger employeeId);

    List<Employee> findEmployeesBySupervisorIdIsNull();

    Employee findEmployeesById(BigInteger employeeId);

}
