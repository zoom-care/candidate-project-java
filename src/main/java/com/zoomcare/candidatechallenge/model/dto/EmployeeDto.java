package com.zoomcare.candidatechallenge.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonPropertyOrder(value = {"employeeId", "supervisorId", "properties", "directReports"})
public class EmployeeDto {

    private Long employeeId;
    private Long supervisorId;
    private Map<String, String> properties;
    private List<EmployeeDto> directReports;
}
