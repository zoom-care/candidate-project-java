package com.zoomcare.candidatechallenge.model.db;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "PROPERTY")
public class Properties {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private BigInteger employeeId;
    @Column(name = "KEY")
    private String key;
    @Column(name = "VALUE")
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
