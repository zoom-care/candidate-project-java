package com.zoomcare.candidatechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.ServiceImpl.PropertyServiceImpl;
import com.zoomcare.candidatechallenge.entity.Property;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

	@Autowired
	private PropertyServiceImpl propertyService;

	@GetMapping
	public List<Property> getProperties() {
		return propertyService.findAllProperties();
	}

}
