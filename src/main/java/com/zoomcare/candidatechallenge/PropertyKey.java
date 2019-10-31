package com.zoomcare.candidatechallenge.model;
import javax.persistence.*;
import java.io.Serializable;

/*
 * Class to represent composite key of properties table
 * (employee_id, key)
 */
@Embeddable
public class PropertyKey implements Serializable {

    @Column
    private Long employee_id;

    @Column
    private String key;

    public PropertyKey(){
    }

    public PropertyKey(Long employee_id, String key) {
        this.employee_id = employee_id;
        this.key = key;
    }

    public Long getEmployeeId() {
        return employee_id;
    }
    public void setEmployeeId(Long employee_id) {
        this.employee_id = employee_id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals (Object o) {
        if (!(o instanceof PropertyKey)) {
            return false;
        }
        PropertyKey that = (PropertyKey)o;
        return ((this.getEmployeeId() == that.getEmployeeId()) &&
               (this.getKey().equals(that.getKey())));
    }

    @Override 
    public int hashCode() {
        return (getEmployeeId().hashCode() ^ getKey().hashCode());
    } 
}   
