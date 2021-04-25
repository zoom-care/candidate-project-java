package com.zoomcare.candidatechallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
    private JdbcTemplate jtm;
	
	
	/*
	 * Gets all employees which is ordered by supervisor id
	 */
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employee ORDER BY supervisor_id";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }
    
    /*
     * Gets the employee by the id
     */
    public Employee getById(Long id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        return jtm.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(Employee.class));
    }
    
    /*
     * Gets the direct reports for the employee
     */
    public List<Employee> getDirectReports(Long id) {
    	String sql = "SELECT * FROM employee WHERE supervisor_id = " + id.toString();
        return jtm.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }
}
