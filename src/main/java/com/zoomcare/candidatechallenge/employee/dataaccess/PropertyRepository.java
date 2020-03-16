package com.zoomcare.candidatechallenge.employee.dataaccess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PropertyRepository {
    private final JdbcTemplate jdbcTemplate;

    public PropertyRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PropertyDAO> getAllByEmployeeId(Long employeeId){
        return jdbcTemplate.query("SELECT EMPLOYEE_ID, KEY, VALUE FROM PROPERTY WHERE EMPLOYEE_ID = ?", new Object[] {employeeId}, (rs, rownum) -> convertRecordToPropertyDao(rs));

    }

    private PropertyDAO convertRecordToPropertyDao(ResultSet rs) {
        try {
            return PropertyDAO.builder()
                    .employeeId(rs.getLong("EMPLOYEE_ID"))
                    .key(rs.getString("KEY"))
                    .value(rs.getString("VALUE"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to map data", e);
        }

    }
}
