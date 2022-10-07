package com.zoomcare.candidatechallenge;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Table("Employee")
public class EmployeeEntity {
    @Id
    private Long id;
    private Long supervisor_id;
    private List<PropertyEntity> employeeDetails;
    private List<Long> reportees;

}
