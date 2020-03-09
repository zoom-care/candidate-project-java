package com.zoomcare.candidatechallenge;

public class EmployeeDO {

    // Properties
    private int id;
    private int supervisorId;

    // Default constructor
    public EmployeeDO() { }

    // Parameterized constructor
    /**
     * Create an employee with the specified ID and supervisor
     * 
     * @param id Employee ID
     * @param supervisorId
     */
    public EmployeeDO(int id, int supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
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

    // Mutators

    public void setId(int id) {
        this.id = id;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }
}