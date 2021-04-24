package com.zoomcare.candidatechallenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.model.Property;

@Service
public class PropertyService {
	
	@Autowired
    private JdbcTemplate jtm;
	
	/*
	 * Gets all properties
	 */
    public List<Property> getAll() {
        String sql = "SELECT * FROM property";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Property.class));
    }
    
    /*
     * Gets property by id
     */
    public List<Property> getById(Long employeeId) {
        String sql = "SELECT * FROM property WHERE employee_id = " + employeeId.toString();
        return jtm.query(sql, new BeanPropertyRowMapper<>(Property.class));
    }
}
