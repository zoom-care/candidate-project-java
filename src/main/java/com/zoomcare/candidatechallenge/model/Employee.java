package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Employee model represents the working data for an employee
 * 
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3042028486047948723L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @Column(name="supervisor_id")
    private Long supervisorId; // auto-boxed because certain employees do not have a supervisor

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private List<Property> properties;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="supervisor_id", referencedColumnName="id")
    private List<Employee> directReports;

    public Employee() {
        this.properties = new ArrayList<Property>();
        this.directReports = new ArrayList<Employee>();
    }

    public Employee(long id, long supervisorId, List<Property> properties, List<Employee> directReports) {
        this.id = id;
        this.supervisorId = supervisorId;
        this.properties = properties;
        this.directReports = directReports;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(long supervisorId) {
        this.supervisorId = supervisorId;
    }

    // the Property objects require information from a different table than the basic Employee fields, but the DAO handles that - not the model itself

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    // Other Employee objects require information from a different records but the DAO handles that - not the model itself

    public List<Employee> getDirectReports() {
        return this.directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    public void addDirectReport(Employee e) {
        this.directReports.add(e);
    }
}