package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.mappers.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        System.out.println(employees);
        return employees;
    }

}
