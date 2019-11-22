package com.zoomcare.candidatechallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Employee {
    Long id;
    String title;
    String region;
    Supervisor supervisor;
    List<Employee> directReports;
}
