package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT DISTINCT e FROM Employee e " +
            " WHERE e.supervisorId = :superVisorId")
    List<Employee> getReports(@Param("superVisorId") final Integer superVisorId);

}
