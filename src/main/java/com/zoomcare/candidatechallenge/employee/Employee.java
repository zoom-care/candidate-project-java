package com.zoomcare.candidatechallenge.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SUPERVISOR_ID")
	@JsonIgnore
	private Employee supervisor;

	@OneToMany
	@JoinColumn(name = "SUPERVISOR_ID")
	private Set<Employee> reports;

	@OneToMany
	@JoinColumn(name = "EMPLOYEE_ID")
	private Set<Properties> properties;
}
