package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, BigInteger> {

    @Query("select e from Employee e where e.supervisor.id is null")
    List<Employee> getTopEmployees();

}
