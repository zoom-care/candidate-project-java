package com.zoomcare.candidatechallenge.employee.view;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class EmployeeViewModel {
    Long id;
    Long supervisorId;
    List<PropertyViewModel> properties;
    List<EmployeeViewModel> directReports;
}
