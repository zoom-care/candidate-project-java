package com.zoomcare.candidatechallenge.datamarshal;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee emp = new Employee();
        String idString = resultSet.getString("id");
        if (idString != null) {
            emp.setId(new BigInteger(idString));
        }
        String supervisorIdString = resultSet.getString("supervisor_id");
        if (supervisorIdString != null) {
            emp.setSupervisorId(new BigInteger(supervisorIdString));
        }
        return emp;
    }
}
