package com.zoomcare.candidatechallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private List<PropertiesDTO> properties;
    private List<EmployeeDTO> reports;

    public EmployeeDTO() {

    }

    public EmployeeDTO(long l) {
    }

    // Getters and setters
}
