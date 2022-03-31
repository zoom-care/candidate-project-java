package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
public class PropertyPK implements Serializable {

    @Column(name = "employee_id")
    private BigInteger employeeId;
    private String key;

    public BigInteger getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
