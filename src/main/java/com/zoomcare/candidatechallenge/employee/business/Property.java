package com.zoomcare.candidatechallenge.employee.business;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Property {
    Long employeeId;
    String key;
    String value;
}
