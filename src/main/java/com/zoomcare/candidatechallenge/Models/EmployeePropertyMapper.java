package com.zoomcare.candidatechallenge.Models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePropertyMapper implements RowMapper<EmployeeProperty> {
    @Override
    public EmployeeProperty mapRow(ResultSet resultSet, int i) throws SQLException {
        return new EmployeeProperty(
                resultSet.getLong("EMPLOYEE_ID"),
                resultSet.getString("KEY"),
                resultSet.getString("VALUE"));
    }
}
