package com.zoomcare.candidatechallenge.dto;

import java.math.BigInteger;
import java.util.List;

public class EmployeePropertiesDTO {

    private BigInteger id;
    private BigInteger superVisorId;
    private List<PropertiesDTO> properties;
    private List<EmployeePropertiesDTO> employees;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSuperVisorId() {
        return superVisorId;
    }

    public void setSuperVisorId(BigInteger superVisorId) {
        this.superVisorId = superVisorId;
    }

    public List<PropertiesDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertiesDTO> properties) {
        this.properties = properties;
    }

    public List<EmployeePropertiesDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeePropertiesDTO> employees) {
        this.employees = employees;
    }
}
