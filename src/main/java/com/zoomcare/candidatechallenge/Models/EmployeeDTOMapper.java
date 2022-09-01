package com.zoomcare.candidatechallenge.Models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDTOMapper implements RowMapper<EmployeeDTO> {

    @Override
    public EmployeeDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new EmployeeDTO(
                resultSet.getLong("ID"),
                resultSet.getLong("SUPERVISOR_ID"),
                resultSet.getString("KEY"),
                resultSet.getString("VALUE")
        );
    }
}
