package com.zoomcare.candidatechallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zoomcare.candidatechallenge.entities.Property;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    int employeeId;
    List<Map<String, String>> properties;
    List<EmployeeDto> subEmployees;

    public void setProperties(List<Property> properties) {
        this.properties = new ArrayList<>();
        for (Property property : properties) {
            Map<String, String> map = new HashMap<>();
            map.put(property.getKey(), property.getValue());
            this.properties.add(map);
        }
    }
}
