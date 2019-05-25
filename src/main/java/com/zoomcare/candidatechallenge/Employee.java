package com.zoomcare.candidatechallenge;

import java.util.ArrayList;

/**
 * Created by gwjense on 5/23/19.
 */
public class Employee {
    private Long employeeId;
    private Long supervisorId;
    private String title;
    private String propertyValue;
    private ArrayList<Employee> employees;

    public Employee(Long employeeId, Long supervisorId) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        this.title = "";
        this.propertyValue = "";
        this.employees = new ArrayList<>();

    }

    public Employee(Long employeeId, Long supervisorId, String title, String propertyValue) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        this.title = title;
        this.propertyValue = propertyValue;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
