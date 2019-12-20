package com.zoomcare.candidatechallenge.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.repo.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long >{
	
	@EntityGraph("Employee.directReports")
	List<Employee> findBySupervisorIdIsNull();
	
	@EntityGraph("Employee.directReports")
	Optional<Employee> findById(Long id);
}
