package com.zoomcare.candidatechallenge.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "property")
public class EmployeePropertyEntity implements Serializable {
  
  @Id
  @Column(name = "employee_id")
  private Long employeeId;
  
  @Id
  @Column(name = "key")
  private String key;
  
  @Column(name = "value")
  private String value;
}
