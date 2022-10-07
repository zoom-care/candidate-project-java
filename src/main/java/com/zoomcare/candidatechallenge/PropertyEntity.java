package com.zoomcare.candidatechallenge;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Property")
public class PropertyEntity {
    @Id
    private Long employeeId;
    private String key;
    private String value;
}
