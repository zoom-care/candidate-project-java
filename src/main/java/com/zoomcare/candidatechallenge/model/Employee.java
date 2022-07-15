package com.zoomcare.candidatechallenge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity representing EMPLOYEE table
 *
 * @author Sergio de la Torre
 * @version 1.0
 * @since 2022-07-14
 */
@Entity
@Table( name="EMPLOYEE" )
@Data
public class Employee {
	
	@Id
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "SUPERVISOR_ID")
	private Employee supervisor;
	
	@OneToMany( mappedBy = "supervisor", fetch = FetchType.LAZY)
	private List<Employee> employees = new ArrayList<>();
	
	@OneToMany( mappedBy = "employee")
	private List<Property> properties = new ArrayList<>();

}
