package com.zoomcare.candidatechallenge.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.models.hibernate.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    

    //This table has emp ID and sup ID.  

    Optional<List<Employee>> findBySupervisorId(long supervisorId);
    Optional<Employee> findByEmployeeId(long employeeId);
}
