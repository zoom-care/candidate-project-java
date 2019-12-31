package com.zoomcare.candidatechallenge;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long employeeId;

	@Column(name = "SUPERVISOR_ID")
	Long supervisorId;

	@OneToMany(mappedBy = "supervisorId")
	List<Employee> directReports;

	@ElementCollection
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	@CollectionTable(name = "PROPERTY", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
	Map<String, String> properties;

	public long getEmployeeId() {
		return employeeId;
	}

	public List<Employee> getDirectReports() {
		return directReports;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

}
