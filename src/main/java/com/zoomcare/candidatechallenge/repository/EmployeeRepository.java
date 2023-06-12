package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllBySupervisorId(Long id);
}