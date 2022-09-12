package com.zoomcare.candidatechallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.entities.EmployeeResource;
import com.zoomcare.candidatechallenge.entities.EmployeeResourceMapper;
import com.zoomcare.candidatechallenge.entities.PropertiesRowMapper;

@Service
public class EmployeeService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<EmployeeResource> getTopLevelEmployees() {
		String getTopLevelQuery = "Select * from employee where supervisor_id is null";
		List<EmployeeResource> resources = jdbcTemplate.query(getTopLevelQuery, new EmployeeResourceMapper());
		for(EmployeeResource r:resources) {
			r.setEmployeeProperties(getEmployeeProps(r.getEmployeeId()));
		}
		for(EmployeeResource r:resources) {
			r.setDirectReports(getDirectReports(r.getEmployeeId()));
		}
		return resources;
	}
	
	public EmployeeResource getEmployeeById(int employeeId) {
		EmployeeResource r = new EmployeeResource();
		r.setEmployeeId(employeeId);
		r.setEmployeeProperties(getEmployeeProps(employeeId));
		r.setDirectReports(getDirectReports(employeeId));
		return r;
	}
	
	public List<List<String>> getEmployeeProps(int id){
		String emplPropQuery = "Select * from property where employee_id="+id;
		return jdbcTemplate.query(emplPropQuery, new PropertiesRowMapper());
	}
	
	public List<EmployeeResource> getDirectReports(int id){
		String getDirectReportsofId = "Select * from employee where supervisor_id = "+id;
		return jdbcTemplate.query(getDirectReportsofId, new EmployeeResourceMapper());
	}

}
