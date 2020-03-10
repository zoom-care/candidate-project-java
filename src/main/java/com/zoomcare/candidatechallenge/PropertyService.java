package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    public IPropertyRepository repository;

    public PropertyService(IPropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> getByEmployee(int employeeId) throws Exception {
        return PropertyMapper.map(repository.getByEmployee(employeeId));
    }
}