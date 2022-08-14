package com.zoomcare.candidatechallenge.services.impl;

import com.zoomcare.candidatechallenge.models.entity.Employee;
import com.zoomcare.candidatechallenge.models.entity.Property;
import com.zoomcare.candidatechallenge.models.entity.PropertyPKId;
import com.zoomcare.candidatechallenge.models.repository.IEmployeeRepository;
import com.zoomcare.candidatechallenge.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Property> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Property> findByEmployeeId(Long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }


}
