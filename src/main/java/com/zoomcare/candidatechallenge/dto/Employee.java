package com.zoomcare.candidatechallenge.dto;

import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    Long id;
    String title;
    String region;
    Supervisor supervisor;
}
