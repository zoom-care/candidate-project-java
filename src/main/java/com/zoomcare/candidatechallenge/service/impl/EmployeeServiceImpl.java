package com.zoomcare.candidatechallenge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.bean.EmployeeBean;
import com.zoomcare.candidatechallenge.bean.PropertyBean;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public EmployeeBean findById(Long id) {
		
		Employee employeeEntity =  this.repository.findById(id).get();
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setId(employeeEntity.getId());
		for (Property property : employeeEntity.getProperties()) {
			employeeBean.addPropertyBean(new PropertyBean(property));
		}
		for (Employee employee : employeeEntity.getDirectReports()) {
			EmployeeBean directReport = new EmployeeBean();
			directReport.setId(employee.getId());
			directReport.addProperties(employee.getProperties());
			employeeBean.addDirectReport(directReport);
		}
				
		return employeeBean;
	}

	@Override
	public List<EmployeeBean> findTopLevelEmployee() {
		
		List<EmployeeBean> employeeBeans = new ArrayList<>();
		List<Employee> employees = this.repository.findAll();
		for (Employee employee : employees) {
			employeeBeans.add(new EmployeeBean(employee, employee.getDirectReports()));
		}
		
		return employeeBeans;
	}

}