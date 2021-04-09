package com.zoomcare.candidatechallenge.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class EmployeeDTO{
    private BigInteger id;
    private BigInteger supervisorId;
    private List<EmployeePropertyDTO> properties;
    private List<EmployeeDTO> directReports;

}
