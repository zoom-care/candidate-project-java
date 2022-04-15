package com.zoomcare.candidatechallenge.models.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.zoomcare.candidatechallenge.models.entity.Employee;
import com.zoomcare.candidatechallenge.models.entity.Property;


public interface IEmployeeService {
	public List<Employee> findAll();
	
	public Employee save(Employee employee);
	
	public Employee findById(Long id);
	
	public void delete(Long id);

	public List<HashMap<Employee, List<Property>>> findAllWithProperties();

	public HashMap<Optional<Employee> , List<Property>> findProperties(Long id);
}
