package com.zoomcare.candidatechallenge.dto;

import java.util.List;
import java.util.Map;

public class EmployeeDTO {

    private Long id;
    private Long supervisorId;

    private List<PropertyDTO> properties;

    private List<EmployeeDTO> employees;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, Long supervisorId, List<PropertyDTO> properties, List<EmployeeDTO> employees) {
        this.id = id;
        this.supervisorId = supervisorId;
        this.properties = properties;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }
}
