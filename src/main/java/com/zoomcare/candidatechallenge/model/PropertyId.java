package com.zoomcare.candidatechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyId implements Serializable {

    private Long employeeId;

    private String key;
}
