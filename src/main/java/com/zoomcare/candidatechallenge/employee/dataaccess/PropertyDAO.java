package com.zoomcare.candidatechallenge.employee.dataaccess;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PropertyDAO {

    public Long employeeId;

    public String key;

    public String value;
}
