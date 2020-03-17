package com.zoomcare.candidatechallenge.mapper;

import com.zoomcare.candidatechallenge.model.EmployeeDBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeRowMapper implements RowMapper<EmployeeDBO> {
    public EmployeeDBO mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeDBO employee = new EmployeeDBO();
        employee.setId(rs.getInt("ID"));
        employee.setSupervisorId(rs.getInt("SUPERVISORID"));
        employee.setEmployeeId(rs.getInt("EMPLOYEEID"));
        employee.setKey(rs.getString("KEY"));
        employee.setValue(rs.getString("VALUE"));
        return employee;
    }
}
