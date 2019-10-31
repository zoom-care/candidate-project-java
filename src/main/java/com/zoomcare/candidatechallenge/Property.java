package com.zoomcare.candidatechallenge.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

/*
 * Class to represent an entry in the properties table
 */
@Entity
@Table(name = "Property")
public class Property {
    @EmbeddedId
    private PropertyKey property_id;

    private String value;

    @ManyToOne
    @JoinColumn(name="employee_id", insertable=false, updatable=false)
    private Employee employee;

    public Property(){
        super();
    }

    public Property(Long employee_id, String key, String value) {
        super();
        this.property_id = new PropertyKey(employee_id,key);
        this.value = value;
    }

    @JsonIgnore
    public PropertyKey getPropertyId() {
        return property_id;
    }
    public void setPropertyId(PropertyKey id) {
        this.property_id = id;
    }
    public String getKey() {
        return property_id.getKey();
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @JsonIgnore
    public Employee getEmployee(){
        return this.employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
