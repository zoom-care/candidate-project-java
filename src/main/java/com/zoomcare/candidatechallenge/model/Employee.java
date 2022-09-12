package com.zoomcare.candidatechallenge.model;

import org.springframework.data.annotation.Id;

public class Employee {

    @Id
    private Integer id;
    private Integer supervisorId;

    public Employee(Integer id, Integer supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    public Employee() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", supervisorId='" + supervisorId + '\'' +
                '}';
    }
}
