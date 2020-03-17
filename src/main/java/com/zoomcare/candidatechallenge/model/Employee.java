package com.zoomcare.candidatechallenge.model;

import java.util.HashMap;
import java.util.HashSet;

public class Employee {
    private int supervisorId;
    private int employeeId;
    private HashMap<String, String> titleRegionMap = new HashMap<>();
    private HashSet<Employee> reports;

    public Employee(int employeeId, int supervisorId, String key, String value) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        titleRegionMap.put(key, value);
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public HashMap<String, String> getTitleRegionMap() {
        return titleRegionMap;
    }

    public void setTitleRegionMap(HashMap<String, String> titleRegionMap) {
        this.titleRegionMap = titleRegionMap;
    }

    public HashSet<Employee> getReports() {
        return reports;
    }

    public void setReports(HashSet<Employee> reports) {
        this.reports = reports;
    }
}
