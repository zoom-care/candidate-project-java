package com.zoomcare.candidatechallenge.model;

import java.util.HashMap;
import java.util.HashSet;

public class Employee {
    private int supervisorId;
    private int employeeId;
    private HashMap<String, String> title = new HashMap<>();
    private HashSet<Employee> reports;

    public Employee(int employeeId, int supervisorId, String key, String value) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        title.put(key, value);
        reports = new HashSet<>();
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public HashMap<String, String> getTitle() {
        return title;
    }

    public HashSet<Employee> getReports() {
        return reports;
    }
}
