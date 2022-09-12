package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate nPJdbcTemplate;

    @Override
    public List<Employee> findAllSupervisors() {
        List<Employee> employees = jdbcTemplate.query("SELECT * FROM employee WHERE supervisor_id IS NULL OR supervisor_id = 1",

                new RowMapper<Employee>(){

                    @Override
                    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        Employee employee = new Employee();
                        employee.setId(resultSet.getInt("id"));
                        employee.setSupervisorId(resultSet.getInt("supervisor_id"));
                        return employee;
                    }
                });

        return employees;
    }

    @Override
    public List<Employee> findBySupervisorId(Integer supervisorId) {

        List<Employee> employees = nPJdbcTemplate.query("SELECT * FROM employee WHERE supervisor_id = :supervisorId",
                new MapSqlParameterSource().addValue("supervisorId", supervisorId),

                new RowMapper<Employee>(){

            @Override
            public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setSupervisorId(resultSet.getInt("supervisor_id"));
                return employee;
            }
        });

        return employees;
    }
}
