package com.zoomcare.candidatechallenge.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Employee model represents the working data for an employee
 * 
 */
public class Employee {

    private long id;
    private long supervisorId;
    private List<Property> properties;
    private List<Employee> directReports;

    public Employee() {
        this.properties = new ArrayList<Property>();
        this.directReports = new ArrayList<Employee>();
    }

    public Employee(long id, long supervisorId, List<Property> properties, List<Employee> directReports) {
        this.id = id;
        this.supervisorId = supervisorId;
        this.properties = properties;
        this.directReports = directReports;
    }

    // TODO: hibernate or some other ORM framework could provide functions to auto join the appropriate tables for this

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(long supervisorId) {
        this.supervisorId = supervisorId;
    }

    // the Property objects require information from a different table than the basic Employee fields, but the DAO handles that - not the model itself

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    // Other Employee objects require information from a different records but the DAO handles that - not the model itself

    public List<Employee> getDirectReports() {
        return this.directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    public void addDirectReport(Employee e) {
        this.directReports.add(e);
    }
}