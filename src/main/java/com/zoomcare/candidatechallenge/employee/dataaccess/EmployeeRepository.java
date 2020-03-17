package com.zoomcare.candidatechallenge.employee.dataaccess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmployeeDAO> getAllBySupervisorId(Long supervisorId){
        return jdbcTemplate.query("SELECT ID, SUPERVISOR_ID FROM EMPLOYEE WHERE SUPERVISOR_ID = ?", new Long[] {supervisorId}, (rs, rownum) -> convertRecordToEmployeeDao(rs));
    }
    public Optional<EmployeeDAO> getById(Long id) {
        return jdbcTemplate.query("SELECT ID, SUPERVISOR_ID FROM EMPLOYEE WHERE SUPERVISOR_ID = ?", new Long[] {id}, (rs, rownum) -> convertRecordToEmployeeDao(rs)).stream().findFirst();
    }
    public List<EmployeeDAO> getAllBySupervisorIdNull(){
        return jdbcTemplate.query("SELECT ID, SUPERVISOR_ID FROM EMPLOYEE WHERE SUPERVISOR_ID IS NULL", (rs, rownum) -> convertRecordToEmployeeDao(rs));
    }

    private EmployeeDAO convertRecordToEmployeeDao(ResultSet rs){
        try {
            return EmployeeDAO.builder()
                    .id(rs.getLong("ID"))
                    .supervisorId(rs.getLong("SUPERVISOR_ID"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to map data", e);
        }
    }


}
