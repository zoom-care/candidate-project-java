package com.zoomcare.candidatechallenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.zoomcare.candidatechallenge.domain.Employee;

// Spring Data Rest creates automatically Restful webService for each entity
// REST return JSOn in the HAL
// Endpoint defined by : spring.data.rest.basePath in application.yml. 

@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository <Employee, Long>{
	

}
