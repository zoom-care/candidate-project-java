package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "PROPERTY")
public class Properties {

    @Id
    @Column
    private BigInteger employeeId;
    private String key;
    private String value;

    public Properties() {
    }

    public Properties(BigInteger employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }

    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Properties{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", key='").append(key).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
