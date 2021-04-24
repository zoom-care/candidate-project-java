package com.zoomcare.candidatechallenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeProperty;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	

	@PersistenceContext
	private EntityManager entityManager;
	private List<EmployeeProperty> employeePropertyList;

	
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll()
		.forEach(employees::add); // method reference, Java 8 Lambda
		return employees;
	}
	
	
	public Employee getEmployee(String id) {
		int idAsInt = Integer.parseInt(id);
		return employeeRepository.findById(idAsInt).orElse(null);
	}

	
//	public List<Employee> getAllRecords(){
//		// inner join
//	    Query query 
//	      = entityManager.createQuery("SELECT e.id, e.supervisorId, p.key, p.value FROM Employee e JOIN Property p ON e.id = p.employeeId");
//
//		List<Employee> employeePropertyList= query.getResultList();
//		this.employeePropertyList = employeePropertyList;
//	    return employeePropertyList;
//		
//	}
	
	
	public List<EmployeeProperty> getAllRecords(){
		// inner join
	    Query query 
	      = entityManager.createQuery("SELECT new com.zoomcare.candidatechallenge.model.EmployeeProperty(e.id, e.supervisorId, p.key, p.value)"
	      		+ " FROM Employee e JOIN Property p ON e.id = p.employeeId");
	    		  
		this.employeePropertyList= query.getResultList();
	    return employeePropertyList;
		
	}
	
	
	
	
	// the primary key must be unique, but might have two rows in table after join
	public List<EmployeeProperty> getEmployeeWithProperties(int argId) {
		
		if(employeePropertyList == null)
			getAllRecords();
		if(employeePropertyList.isEmpty())
			return null;
		
		return employeePropertyList.stream()
			      .filter(ep -> ep.getId() == argId)
			      .collect(Collectors.toList());
		
		//return employeePropertyList.stream().filter(ep -> ep.getId() == argId).findFirst().get();
		
		
		// not a native query
//		Query query 
//	      = entityManager.createQuery("SELECT new com.zoomcare.candidatechallenge.model.EmployeeProperty(e.id, e.supervisorId, p.key, p.value) "
//	      		+ "FROM Employee e JOIN Property p ON e.id = p.employeeId "
//	      		+ "where e.id=:argId");
//	    query.setParameter("argId", argId); 
//		EmployeeProperty employeeProperty= (EmployeeProperty) query.getSingleResult();
//	    return employeeProperty;
		
		
	}
	

}
