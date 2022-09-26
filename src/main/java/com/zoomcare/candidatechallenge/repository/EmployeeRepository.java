package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.supervisor IS NULL")
    List<Employee> getAllTopLevelEmployees();
}
