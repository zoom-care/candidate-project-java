package com.zoomcare.candidatechallenge.model.db;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Map;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name="ID")
    private BigInteger id;
    @Column(name="SUPERVISOR_ID")
    private BigInteger supervisorId;

    public Employee() {
    }

    public Employee(BigInteger id, BigInteger supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    public BigInteger getId() {
        return id;
    }

    public BigInteger getSupervisorId() {
        return supervisorId;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setSupervisorId(BigInteger supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", supervisorId=").append(supervisorId);
        sb.append('}');
        return sb.toString();
    }
}
