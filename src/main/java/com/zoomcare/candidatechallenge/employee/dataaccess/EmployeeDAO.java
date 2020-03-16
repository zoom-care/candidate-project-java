package com.zoomcare.candidatechallenge.employee.dataaccess;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Value
@Builder(toBuilder = true)
public class EmployeeDAO {
    @Id
    @Column("ID")
    Long id;

    @Column("SUPERVISOR_ID")
    Long supervisorId;
}
