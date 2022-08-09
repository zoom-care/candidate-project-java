package com.zoomcare.candidatechallenge.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class Property implements Serializable  {

    private static final long serialVersionUID = 5447657273429089790L;
    @Id
    @Column(name = "employee_id")
    private Long employeeId;
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public Property() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public String toString() {
        return "Property{" +
                "employeeId=" + employeeId +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
