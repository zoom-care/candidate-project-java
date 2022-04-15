package com.zoomcare.candidatechallenge.models.services;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomcare.candidatechallenge.models.dao.IEmployeeDao;
import com.zoomcare.candidatechallenge.models.dao.IPropertyDao;
import com.zoomcare.candidatechallenge.models.entity.Employee;
import com.zoomcare.candidatechallenge.models.entity.Property;


@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired
	private IPropertyDao propertyDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return (List<Employee>) employeeDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HashMap<Employee, List<Property>>> findAllWithProperties() {
		List<Employee> employees = (List<Employee>) employeeDao.findAll();
		List<HashMap<Employee, List<Property>>> returnList = null;
		employees.forEach(employee -> {
			List<Property> properties = (List<Property>) propertyDao.findByEmployeeId(employee.getId());
			HashMap<Employee, List<Property>> map = new HashMap<Employee, List<Property>>();
			map.put(employee, properties);
			returnList.add(map);
		});

		return returnList;
	}	
	
	@Transactional(readOnly = true)
	public HashMap<Optional<Employee>, List<Property>> findProperties(Long id) {
		Optional<Employee> employee = employeeDao.findById(id);
		List<Property> properties = (List<Property>) propertyDao.findByEmployeeId(id);
		HashMap<Optional<Employee>, List<Property>> returnMap = null;
		returnMap.put(employee, properties);

		return returnMap;
	}	

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findById(Long id) {
		return employeeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		employeeDao.deleteById(id);
	}

}