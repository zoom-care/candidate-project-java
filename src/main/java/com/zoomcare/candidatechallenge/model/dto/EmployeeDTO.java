package com.zoomcare.candidatechallenge.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDTO implements Serializable
{
    private long id;
    private List<EmployeeDTO> employees;
    private List<PropertyDTO> properties;
}
