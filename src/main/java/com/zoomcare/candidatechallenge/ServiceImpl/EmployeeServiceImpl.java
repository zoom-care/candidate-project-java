package com.zoomcare.candidatechallenge.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.Service.EmployeeService;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepo.findById(id);
	}

}
