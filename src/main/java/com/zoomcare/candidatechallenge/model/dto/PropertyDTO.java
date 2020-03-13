package com.zoomcare.candidatechallenge.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PropertyDTO implements Serializable
{
    private long employeeId;
    private String key;
    private String value;
}
