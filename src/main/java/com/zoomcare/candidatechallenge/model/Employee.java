package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zoomcare.candidatechallenge.exception.EmployeePropertyNotFoundExcpetion;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @ApiModelProperty(name = "Id", position = 0, required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(name = "Properties", position = 1)
    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(
            name = "property",
            joinColumns = {@JoinColumn(name = "employee_id")})
    Map<String, String> properties = new HashMap<>();

    @JsonIgnore
    @Column(name = "supervisor_id")
    private Long supervisorId;

    @ApiModelProperty(name = "Direct reports", position = 2, dataType = "Employee")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private List<Employee> directReports = new ArrayList<>();

    public Employee() {
    }

    public Employee(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Employee(Map<String, String> properties, Long supervisorId) {
        this.properties = properties;
        this.supervisorId = supervisorId;
    }

    public Employee(Long id, Map<String, String> properties, Long supervisorId) {
        this.id = id;
        this.properties = properties;
        this.supervisorId = supervisorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(final Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(final List<Employee> directReports) {
        this.directReports = directReports;
    }

    public void addDirectReports(final Employee employee) {
        this.directReports.add(employee);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

    public void addProperty(final String name, final String value) {
        this.properties.put(name, value);
    }
}
