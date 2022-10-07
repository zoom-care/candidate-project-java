package com.zoomcare.candidatechallenge;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.util.Set;

@Data
@Table("Employee")
public class EmployeeEntity {
    @Id
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="supervisor_id")
    private EmployeeEntity supervisor;

    @OneToMany(mappedBy = "supervisor")
    private Set<EmployeeEntity> subordinates;

}
