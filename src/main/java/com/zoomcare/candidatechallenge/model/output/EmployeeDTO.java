package com.zoomcare.candidatechallenge.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private Integer supervisorId;
    private Map<String, String> properties;
    private List<EmployeeDTO> directEmployees;
}
