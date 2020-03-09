package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    // Properties
    private int id;
    private int supervisorId;
    private List<Employee> directReports;

    // Default constructor
    public Employee() {
        this.directReports = new ArrayList<>();
     }

    // Parameterized constructor
    /**
     * Create an employee with the specified ID and supervisor
     * 
     * @param id Employee ID
     * @param supervisorId
     */
    public Employee(int id, int supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
        this.directReports = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Employee: { id: " + this.id + ", supervisorId: " + this.supervisorId + " }";
    }

    // Accessors

    /**
     * @return Employee ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return Supervisor ID
     */
    public int getSupervisorId() {
        return supervisorId;
    }

    /**
     * @return List of direct reports
     */
    public List<Employee> getDirectReports() {
        return directReports;
    }

    // Mutators

    public void setId(int id) {
        this.id = id;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    public void pushDirectReport(Employee employee) {
        this.directReports.add(employee);
    }

    public void popDirectReport() {
        this.directReports.remove(directReports.size() - 1);
    }

    public void clearDirectReports() {
        this.directReports.clear();
    }
}