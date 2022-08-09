package com.zoomcare.candidatechallenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Collection;

public class EmployeeResponseDTO {

    private long employeeId;
    private Collection<PropertyResponseDTO> properties;
    @JsonInclude(Include.NON_NULL)
    private Collection<EmployeeResponseDTO> directSubordinates;

    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Collection<PropertyResponseDTO> getProperties() {
        return properties;
    }

    public void setProperties(Collection<PropertyResponseDTO> properties) {
        this.properties = properties;
    }

    public Collection<EmployeeResponseDTO> getDirectSubordinates() {
        return directSubordinates;
    }

    public void setDirectSubordinates(
            Collection<EmployeeResponseDTO> directSubordinates) {
        this.directSubordinates = directSubordinates;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id=" + employeeId +
                ", properties=" + properties +
                '}';
    }
}
