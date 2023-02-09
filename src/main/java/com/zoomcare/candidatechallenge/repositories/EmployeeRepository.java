package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.models.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Access layer for the employee table.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT * FROM Employee WHERE id = :id")
    Optional<Employee> findById(Long id);

    @Query("SELECT * FROM Employee WHERE supervisor_id = :supervisorId")
    Optional<List<Employee>> findDirectReports(Long supervisorId);

    @Query("SELECT * FROM Employee WHERE supervisor_id IS NULL")
    Optional<List<Employee>> findBySupervisorIdIsNull();
}