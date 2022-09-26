package com.zoomcare.candidatechallenge.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {
  
  @Id
  private Long id;
  
  @Column(name = "supervisor_id")
  private Long supervisorId;
  
  @OneToMany
  @JoinColumn(name = "employee_id")
  private List<EmployeePropertyEntity> properties;
  
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "supervisor_id")
  private List<EmployeeEntity> directReports;
}
