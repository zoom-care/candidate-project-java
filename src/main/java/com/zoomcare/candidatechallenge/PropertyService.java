
package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    public List<Property> getAllPropertys() {
	List<Property> propertys = new ArrayList<Property>();
	propertyRepository.findAll().forEach(property -> propertys.add(property));
	return propertys;
    }

    public List<Property> getPropertysById(int id) {
	List<Property> propertys = new ArrayList<Property>();
	propertyRepository.findAll().forEach(property -> propertys.add(property));
	return propertys;
    }

}
