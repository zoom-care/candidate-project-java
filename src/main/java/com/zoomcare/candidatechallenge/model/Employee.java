package com.zoomcare.candidatechallenge.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    public Employee() {

    }

    public Employee(Integer id, Integer supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supervisor_id")
    private Integer supervisorId;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="supervisor_id")
    private List<Employee> employees;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private List<Properties> properties;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
