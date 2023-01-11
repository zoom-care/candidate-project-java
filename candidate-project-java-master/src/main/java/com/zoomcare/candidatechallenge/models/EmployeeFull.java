package com.zoomcare.candidatechallenge.models;

import java.util.List;

import com.zoomcare.candidatechallenge.models.hibernate.Employee;
import com.zoomcare.candidatechallenge.models.hibernate.Property;

import lombok.Data;

@Data
public class EmployeeFull {
    
    private long employeeId;
    private long supervisorId;

    private List<Property> employeeProperties;
    private List<Employee> reportingEmployees;
}
