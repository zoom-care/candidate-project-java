package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;

public interface EmployeeRepository extends CrudRepository<Employee, BigInteger> {
}
