package com.zoomcare.candidatechallenge;

import java.util.ArrayList;

/**
 * Created by gwjense on 5/23/19.
 */
public class Employee {
    private int employeeId;
    private int supervisorId;
    private String title;
    private String propertyValue;
    private ArrayList<Employee> employees;

    public Employee(int employeeId, int supervisorId) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        this.title = "";
        this.propertyValue = "";
        this.employees = new ArrayList<>();

    }

    public Employee(int employeeId, int supervisorId, String title, String propertyValue) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
        this.title = title;
        this.propertyValue = propertyValue;
        this.employees = new ArrayList<>();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
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
