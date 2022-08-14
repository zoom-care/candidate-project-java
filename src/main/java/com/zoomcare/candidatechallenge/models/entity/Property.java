package com.zoomcare.candidatechallenge.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {

    @EmbeddedId
    private PropertyPKId propertyPKId;

    private String value;

    @MapsId("employee_id")
    @ManyToOne
    @JoinColumn(name = "employee_id", updatable = false, nullable = false)
    private Employee employee;

    public PropertyPKId getPropertyPKId() {
        return propertyPKId;
    }

    public void setPropertyPKId(PropertyPKId propertyPKId) {
        this.propertyPKId = propertyPKId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
