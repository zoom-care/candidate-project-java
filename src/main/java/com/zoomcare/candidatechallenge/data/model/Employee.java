package com.zoomcare.candidatechallenge.data.model;



import com.fasterxml.jackson.annotation.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "supervisor")
public class Employee {


    @Id
    @Column(name="id")
    private Long id;

    @Column(name = "supervisor_id")
    private Long supervisor_Id;


    @ManyToOne
    @JoinColumn(name = "supervisor_id", insertable = false, updatable = false)
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> employees;


    @OneToMany(mappedBy = "employee")
    private List<Property> properties;

    public Employee() {
        this.employees = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupervisor_Id() {
        return supervisor_Id;
    }

    public void setSupervisor_Id(Long supervisor_Id) {
        this.supervisor_Id = supervisor_Id;
    }


    public Employee getSupervisor() {
        return supervisor;
    }


    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", supervisor_Id=" + supervisor_Id +
                ", supervisor=" + supervisor +
                ", employees=" + employees +
                ", properties=" + properties +
                '}';
    }
}
