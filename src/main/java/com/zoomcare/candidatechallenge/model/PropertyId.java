package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PropertyId implements Serializable {
    private Long employeeId;
    private String key;
}
