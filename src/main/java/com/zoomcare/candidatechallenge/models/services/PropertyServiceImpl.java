package com.zoomcare.candidatechallenge.models.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomcare.candidatechallenge.models.dao.IPropertyDao;
import com.zoomcare.candidatechallenge.models.entity.Property;


@Service
public class PropertyServiceImpl implements IPropertyService {

	@Autowired
	private IPropertyDao propertyDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Property> findAll() {
		List<Property> employees = (List<Property>) propertyDao.findAll();
		return employees;
	}

	@Override
	@Transactional
	public Property save(Property employee) {
		return propertyDao.save(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public Property findById(Long id) {
		return propertyDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Property> findByEmployeeId(Long employeeId) {
		List<Property> properties = (List<Property>) propertyDao.findAll();
		List<Property> employeeProperties = null;
		properties.forEach(property -> {
			if(property.getEmployee().getId() == employeeId){
				employeeProperties.add(property);
			}
		});		
		return employeeProperties;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		propertyDao.deleteById(id);
	}

}