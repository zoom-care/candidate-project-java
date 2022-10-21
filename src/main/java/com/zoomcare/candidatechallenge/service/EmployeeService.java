package com.zoomcare.candidatechallenge.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.entities.Property;
import com.zoomcare.candidatechallenge.map.EmployeeData;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import com.zoomcare.candidatechallenge.repositories.PropertyRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private PropertyRepository propRepo;

	public ResponseEntity<EmployeeData> getEmployeeData(BigInteger id) {
		
		Optional<Employee> emp = empRepo.findById(id);
		
		if(emp.isPresent()) {
			Optional<List<Property>> props = propRepo.findAllByEmployeeId(id);
			
			return new ResponseEntity<EmployeeData>(EmployeeData.builder().employee(emp.get()).properties(props.get()).build(), HttpStatus.OK);
		}else {
			log.error("The emploee with id {} does not exist.", id);
			return new ResponseEntity<EmployeeData>(HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<List<EmployeeData>> getTopLevelEmployee() {
		
		Optional<List<Employee>> topEmpList =  empRepo.findAllBySupervisoridIsNull();
		
		if(topEmpList.isPresent()) {
			List<EmployeeData> finalList = new ArrayList<>();
			
			topEmpList.get().forEach(employee -> {
				Optional<List<Property>> props = propRepo.findAllByEmployeeId(employee.getId());
				
				finalList.add(EmployeeData.builder().employee(employee).properties(props.get()).build());
			});
			
			return new ResponseEntity<List<EmployeeData>>(finalList, HttpStatus.OK);
		}else {
			
			log.error("There are no top-level employees");
			return new ResponseEntity<List<EmployeeData>>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
