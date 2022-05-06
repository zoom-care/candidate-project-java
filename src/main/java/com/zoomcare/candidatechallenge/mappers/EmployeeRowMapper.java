package com.zoomcare.candidatechallenge.mappers;

import com.zoomcare.candidatechallenge.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper  implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee(
                resultSet.getInt("id"),
                resultSet.getInt("supervisor_id")
        );
    }
}
