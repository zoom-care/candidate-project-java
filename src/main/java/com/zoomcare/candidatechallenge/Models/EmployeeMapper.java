package com.zoomcare.candidatechallenge.Models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee(resultSet.getLong("ID"), resultSet.getLong("SUPERVISOR_ID"));
    }
}
