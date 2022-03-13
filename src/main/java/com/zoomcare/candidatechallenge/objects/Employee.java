package com.zoomcare.candidatechallenge.objects;

public class Employee {
    
    private int id;
    private int supervisorId;
    
    public Employee(int id, int supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    public int getId() {
        return this.id;
    }

    public Employee setID(int id) {
        this.id = id;
        return this;
    }

    public int getSuperVisorId() {
        return this.supervisorId;
    }

    public Employee setSuperVisorID(int supervisorId) {
        this.supervisorId = supervisorId;
        return this;
    }
}
