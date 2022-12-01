package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EmployeeWithReports {

    private Long id;

    private Long supervisorId;

    private Map<String, String> properties;

    private List<EmployeeWithReports> reports;
}
