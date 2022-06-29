package com.zoomcare.candidatechallenge.repository;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name ="employee")
public class Employee implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    private BigInteger id;

    @Column(name = "SUPERVISOR_ID")
    private BigInteger supervisorId;

    @OneToMany(mappedBy = "id.employeeId")
    @JsonManagedReference
    private List<Property> property;

    @Transient
    private Optional<Employee> directReport;

    public Employee() {
    }

    public List<Property> getProperty() {
        return property;
    }

    public void setProperty(List<Property> property) {

        this.property = property;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(BigInteger supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Optional<Employee> getDirectReport() {
        return directReport;
    }

    public void setDirectReport(Optional<Employee> directReport) {
        this.directReport = directReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(supervisorId, employee.supervisorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supervisorId);
    }
}
