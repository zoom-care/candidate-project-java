package com.zoomcare.candidatechallenge.employee.dataaccess;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Value
@Builder(toBuilder = true)
public class PropertyDAO {
    @Id
    @Column("EMPLOYEE_ID")
    public Long employeeId;

    @Id
    @Column("KEY")
    public String key;

    @Column("VALUE")
    public String value;
}
