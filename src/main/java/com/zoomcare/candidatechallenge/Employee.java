package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gwjense on 5/23/19.
 */
public class Employee {
    private Long employeeId;
    private Long supervisorId;
    private HashMap<String, String> properties;
    private ArrayList<Employee> employees;

    public Employee(Long employeeId, Long supervisorId) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        this.properties = new HashMap<>();
        this.employees = new ArrayList<>();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
