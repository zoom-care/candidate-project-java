package com.zoomcare.candidatechallenge.employee.dataaccess;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EmployeeDAO {

    Long id;

    Long supervisorId;
}
