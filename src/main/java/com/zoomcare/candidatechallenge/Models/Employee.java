package com.zoomcare.candidatechallenge.Models;

import org.springframework.lang.Nullable;

import java.util.List;

public class Employee {
    private Long id;

    @Nullable
    private Long supervisorId;

    @Nullable
    private List<EmployeeProperty> employeeProperties;

    public Long getId() {
        return id;
    }

    @Nullable
    public Long getSupervisorId() {
        return supervisorId;
    }

    @Nullable
    public List<EmployeeProperty> getEmployeeProperties() {
        return employeeProperties;
    }

    public void setEmployeeProperties(@Nullable List<EmployeeProperty> employeeProperties) {
        this.employeeProperties = employeeProperties;
    }

    // todo: this can be improved with Lombok
    public Employee(Long id, Long supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }
}
