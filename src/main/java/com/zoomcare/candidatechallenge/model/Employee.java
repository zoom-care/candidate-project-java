package com.zoomcare.candidatechallenge.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	private long id;
	
	@OneToMany
	@JoinColumn(name = "SUPERVISOR_ID")
	private Employee supervisor;
	
	@OneToMany(mappedBy = "supervisor")
	private List<Employee> employees = new ArrayList<>();
	
	
	@OneToMany
	@JoinColumn(name="EMPLOYEE_ID")
	private List<Property> properties = new ArrayList<>();
	
}
