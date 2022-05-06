package com.zoomcare.candidatechallenge.entities;

import lombok.Data;

@Data
public class Employee {
    private int employeeId;
    private int supervisorId;

    public Employee(int employeeId, int supervisorId) {
        this.employeeId = employeeId;
        this.supervisorId = supervisorId;
    }

}
