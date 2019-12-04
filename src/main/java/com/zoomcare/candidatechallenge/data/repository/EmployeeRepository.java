package com.zoomcare.candidatechallenge.data.repository;

import com.zoomcare.candidatechallenge.data.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>{
    List<Employee> findBySupervisor_Id(Long id);
    List<Employee> findAll();
    Employee findEmployeeById(Long id);
}
