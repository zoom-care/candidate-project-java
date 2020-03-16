package com.zoomcare.candidatechallenge.employee.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PropertyViewModel {
    Long employeeId;
    String key;
    String value;
}
