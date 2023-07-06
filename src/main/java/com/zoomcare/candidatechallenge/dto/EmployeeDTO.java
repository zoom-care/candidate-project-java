package com.zoomcare.candidatechallenge.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private Long supervisorId;
    private List<PropertyDTO> properties;
    private List<EmployeeDTO> directReports;
}
