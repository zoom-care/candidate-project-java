package com.zoomcare.candidatechallenge.model.dto;

import java.util.List;

public class EmployeeDTO {

    public int employeeId;
    public List<EmployeePropertyDTO> employeeProperties;
    public List<EmployeeDTO> directReports;

    public EmployeeDTO(int employeeId, List<EmployeePropertyDTO> employeeProperties, List<EmployeeDTO> directReports) {
        this.employeeId = employeeId;
        this.employeeProperties = employeeProperties;
        this.directReports = directReports;
    }

    public EmployeeDTO() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public List<EmployeePropertyDTO> getEmployeeProperties() {
        return employeeProperties;
    }

    public void setEmployeeProperties(List<EmployeePropertyDTO> employeeProperties) {
        this.employeeProperties = employeeProperties;
    }

    public List<EmployeeDTO> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<EmployeeDTO> directReports) {
        this.directReports = directReports;
    }
}
