package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.entities.Property;
import com.zoomcare.candidatechallenge.mappers.EmployeeRowMapper;
import com.zoomcare.candidatechallenge.mappers.PropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Optional<Employee> getEmployeeById(int id) {
        String sql = "SELECT * FROM employee e WHERE e.id = ?";
        return jdbcTemplate.query(sql, new EmployeeRowMapper(), id)
                .stream()
                .findFirst();
    }

    public List<Property> getEmployeeProperties(int employeeId) {
        String sql = "SELECT p.key, p.value FROM property p WHERE p.employee_id = ?";
        return jdbcTemplate.query(sql, new PropertyRowMapper(), employeeId);

    }

}
