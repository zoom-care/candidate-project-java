package com.zoomcare.candidatechallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
@Setter
public class Employee implements Serializable {

  private static final long serialVersionUID = -1970486309310280631L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employee", referencedColumnName = "id")
  private Employee employee;

  @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Employee> employeeList;

}
