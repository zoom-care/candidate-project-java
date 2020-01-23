package com.zoomcare.model;

/**
 * Employee model representing a record in the employee table
 */
public class Employee {

    private long id;
    private long supervisorId;

    public Employee() {
        
    }

    public Employee(long id, long supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    // TODO: hibernate or some other ORM framework could provide functions to auto join the appropriate tables for this
    // TODO: include some annotations here to allow joining on the property table

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

}