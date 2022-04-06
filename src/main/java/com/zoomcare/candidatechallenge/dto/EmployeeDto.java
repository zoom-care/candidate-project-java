package com.zoomcare.candidatechallenge.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDto {
    private Long id;
    private List<EmployeeDto> reporters = new ArrayList<>();
    private List<EmployeePropertyDto> props = new ArrayList<>();
}
