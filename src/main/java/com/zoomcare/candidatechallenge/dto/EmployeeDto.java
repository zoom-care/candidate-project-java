package com.zoomcare.candidatechallenge.dto;

import java.util.List;

public class EmployeeDto {
    private Long employeeId;

    private List<List<String>> employeeProperties;
    private List<EmployeeDto> directReports;

    public EmployeeDto(Long employeeId) {
    this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public List<List<String>> getEmployeeProperties() {
        return employeeProperties;
    }
    public void setEmployeeProperties(List<List<String>> employeeProperties) {
        this.employeeProperties = employeeProperties;
    }
    public List<EmployeeDto> getDirectReports() {
        return directReports;
    }
    public void setDirectReports(List<EmployeeDto> directReports) {
        this.directReports = directReports;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "employeeId=" + employeeId +
                ", employeeProperties=" + employeeProperties +
                ", directReports=" + directReports +
                '}';
    }
}
