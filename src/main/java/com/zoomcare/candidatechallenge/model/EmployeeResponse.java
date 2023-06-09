package com.zoomcare.candidatechallenge.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private Long supervisorId;
    private Iterable<Property> propertyList;
}
