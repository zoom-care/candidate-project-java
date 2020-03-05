package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.db.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, BigInteger> {

    @Query(value = "SELECT * FROM EMPLOYEE ", nativeQuery = true)
    public List<Employee> getAllEmployees();
}
