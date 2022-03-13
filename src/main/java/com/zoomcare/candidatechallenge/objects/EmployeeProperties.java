package com.zoomcare.candidatechallenge.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeProperties {
    private Employee employee;
    private List<Properties> properties;

    public EmployeeProperties(Employee employee, List<Properties> properties) {
        this.employee = employee;
        this.properties = properties;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public EmployeeProperties setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public List<Properties> getProperties() {
        return this.properties;
    }

    public EmployeeProperties setProperties(List<Properties> properties) {
        this.properties = properties;
        return this;
    }
}
