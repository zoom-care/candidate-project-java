package com.zoomcare.candidatechallenge.Models;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private Long id;

    @Nullable
    private Long supervisorId;

    @Nullable
    private List<EmployeeProperty> employeeProperties;

    @Nullable
    private List<Employee> reports = new ArrayList<>();

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

    @Nullable
    public List<Employee> getReports() {
        return reports;
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
