package com.zoomcare.candidatechallenge.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private Long supervisorId;
    private List<PropertyDTO> properties;
    private List<EmployeeDTO> directReports;
}
