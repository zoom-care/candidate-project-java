package com.zoomcare.candidatechallenge.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDto {
    private Long id;
    private List<EmployeeDto> employees = new ArrayList<>();
    private List<PropertyDto> properties = new ArrayList<>();
}
