package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    // Properties
    private int id;
    private int supervisorId;
    private List<Employee> directReports;
    private List<Property> properties;

    // Default constructor
    public Employee() {
        this.directReports = new ArrayList<>();
        this.properties = new ArrayList<>();
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
        this.properties = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{ id: " + this.getId() + ", supervisorId: " + this.getSupervisorId() + ", " +  this.getProperties().toString().replace("[", "").replace("]", "") + " }";
    }

    // Accessors

    /**
     * @return Employee ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return Supervisor ID
     */
    public int getSupervisorId() {
        return this.supervisorId;
    }

    /**
     * @return List of direct reports
     */
    public List<Employee> getDirectReports() {
        return this.directReports;
    }

    /**
     * @return List of properties
     */
    public List<Property> getProperties() {
        return this.properties;
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

    public void setProperties(List<Property> properties) {
        this.properties = properties;
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

    public void pushProperty(Property property) {
        this.properties.add(property);
    }

    public void popProperty() {
        this.properties.remove(properties.size() - 1);
    }

    public void clearProperties() {
        this.properties.clear();
    }
}