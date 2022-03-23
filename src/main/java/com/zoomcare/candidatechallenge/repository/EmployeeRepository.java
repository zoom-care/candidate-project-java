package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, BigInteger> {
    @Query("select e from Employee e where e.supervisor.id is null")
    List<Employee> getTopLevelEmployees();
}
