package com.zoomcare.candidatechallenge.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeResourceMapper implements RowMapper<EmployeeResource>{

	@Override
	public EmployeeResource mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeResource e = new EmployeeResource();
		e.setEmployeeId(rs.getInt("id"));
		return e;
	}
}
