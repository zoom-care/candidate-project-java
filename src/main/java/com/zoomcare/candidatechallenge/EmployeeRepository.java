package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findBySupervisorId(String lastName);

	Employee findById(long id);
}
