package com.zoomcare.candidatechallenge.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {

  private static final long serialVersionUID = -1970486309310280631L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMPLOYEE_ID")
  private Long employeeId;

  @Column(name = "SUPERVISOR_ID")
  private Long supervisorId;

  @Column
  private String title;

  @Column
  private String region;

}
