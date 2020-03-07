package com.zoomcare.candidatechallenge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    // Properties
    @Id
    @GeneratedValue
    private int id;
    private int supervisorId;

    // Default constructor
    public Employee() { }

    // Parameterized constructor
    /**
     * Create an employee with the specified ID and supervisor
     * 
     * @param id Employee ID
     * @param supervisorId
     */
    public Employee(int id, int supervisorId) {
        System.out.println("Employee constructor");
        this.id = id;
        this.supervisorId = supervisorId;
    }

    // Accessors

    /**
     * @return Employee ID
     */
    public int getId() {
        System.out.println("Employee.getId");
        return id;
    }

    /**
     * @return Supervisor ID
     */
    public int getSupervisorId() {
        System.out.println("Employee.getSupervisorId");
        return supervisorId;
    }
}