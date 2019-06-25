package com.zoomcare.candidatechallenge.service.impl;

/*
 * Employee service implementation. This contains all the functions to compose the json returning from the employees endpoint. 
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.zoomcare.candidatechallenge.model.EmployeeDTO;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.service.IServiceEmployee;

@Component("IServiceEmployee")
public class EmployeeServiceImpl implements IServiceEmployee {

	// Database connection (this should really be pooled)
	@Autowired
    JdbcTemplate jdbcTemplate;

	// Create the employee DTO from the result set 
	class EmployeeRowMapper implements RowMapper<EmployeeDTO> {
	    @Override
	    public EmployeeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	EmployeeDTO employee = new EmployeeDTO();
	        employee.setId(rs.getLong("id"));
	        return employee;
	    }
	}

	// Create the property instance from the result set
	class PropertyRowMapper implements RowMapper<Property> {
	    @Override
	    public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Property pair = new Property(rs.getLong("employee_id"), rs.getString("key"),rs.getString("value"));
	        return pair;
	    }
	}
	
	// Get the properties and direct reports for an employee.
	private EmployeeDTO mapEmployeeFields (EmployeeDTO employee) {
		employee = addProperties(employee);
		List<EmployeeDTO> reports = jdbcTemplate.query("select * from employee where supervisor_id=?", new Object[] {employee.getId()}, new EmployeeRowMapper());
		employee.getDirectReports().addAll(reports);
		for (EmployeeDTO reportItems: employee.getDirectReports()) {
			mapEmployeeFields(reportItems);
		}
		return employee;
	}
	
	// Run the property query and populate the employee property map
	private EmployeeDTO addProperties (EmployeeDTO employee) {
		List<Property> props = new ArrayList<Property>();
		try {
			props = jdbcTemplate.query("select * from property where employee_id=?", new Object[] {employee.getId()}, new PropertyRowMapper());
		} catch (Exception e) {
			
		}
		for (Property item: props) {
			employee.getProperties().put(item.getKey(), item.getValue());
		}
		return employee;
	}
	
	// Get the top level employees and recurse through the rest
	@Override
	public List<EmployeeDTO> findAll() {
		List<EmployeeDTO> result = new ArrayList<EmployeeDTO>();
		try {
			result = jdbcTemplate.query("select * from employee where supervisor_id is null", new EmployeeRowMapper());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found");
		}
		for (EmployeeDTO item: result) {
			item = mapEmployeeFields(item);
		}
		return result;
	}

	// Get one employee and all the direct report tree and props
	@Override
	public EmployeeDTO findById(Long id) {
		EmployeeDTO result = null;
		try {
			result = jdbcTemplate.queryForObject("select * from employee where id=?", new Object[] {id}, new EmployeeRowMapper());
			mapEmployeeFields(result);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
		return result;
	}

	@Override
	public Long create(EmployeeDTO employee) {
		return 1l;
	}

	@Override
	public Long getById(Long id) {
		return 1l;
	}

	@Override
	public void update(EmployeeDTO employee) {
	}

	@Override
	public void deleteById(Long id) {
	}

}
