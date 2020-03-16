package com.zoomcare.candidatechallenge.employee.business;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class Employee {
    Long id;
    Long supervisorId;
    List<Property> properties;
    List<Employee> directReports;
}
