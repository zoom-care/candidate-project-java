package com.zoomcare.candidatechallenge.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class PropertyKey implements Serializable {
    private Long employeeId;
    private String key;

}
