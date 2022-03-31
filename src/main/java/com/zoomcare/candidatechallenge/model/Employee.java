package com.zoomcare.candidatechallenge.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
public class Employee {

    @Id
    private BigInteger id;
    
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Property> properties;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> employees;


    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Employee getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
        
}
