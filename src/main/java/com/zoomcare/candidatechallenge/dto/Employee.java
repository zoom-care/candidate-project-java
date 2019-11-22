package com.zoomcare.candidatechallenge.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    Long id;
    String title;
    String region;
    Supervisor supervisor;
}
