package com.zoomcare.candidatechallenge.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.Service.PropertyService;
import com.zoomcare.candidatechallenge.entity.Property;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepo;

	@Override
	public List<Property> findAllProperties() {
		return propertyRepo.findAll();
	}

}
