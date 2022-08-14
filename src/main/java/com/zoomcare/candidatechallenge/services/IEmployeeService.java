package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.models.entity.Property;

import java.util.List;

public interface IEmployeeService {

    public List<Property> findAll();

    public List<Property> findByEmployeeId(Long employeeId);
}
