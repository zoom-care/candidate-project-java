package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("H2EmployeeDao")
public class H2EmployeeDao implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        final String sql = "select id, supervisor_id from employee";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Long id = resultSet.getLong("id");
            Long supervisor_id = resultSet.getLong("supervisor_id");
            Employee employee = new Employee(id, supervisor_id);
            return new Employee(resultSet.getLong("id"), resultSet.getLong("supervisor_id"));
        });
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        final String sql = "select id, supervisor_id from employee where id = ?";
        try {
            Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
                return new Employee(resultSet.getLong("id"), resultSet.getLong("supervisor_id"));
            });
            return Optional.ofNullable(employee);
        } catch (EmptyResultDataAccessException dae) {
            throw new EmployeeNotFoundException(id);
        }
    }

    @Override
    public List<Employee> getEmployeesBySupervisorId(Long supervisor_id) {
        final String sql = "select id, supervisor_id from employee where supervisor_id = ?";
        return jdbcTemplate.query(sql, new Object[]{supervisor_id}, (resultSet, i) -> {
            return new Employee(resultSet.getLong("id"), resultSet.getLong("supervisor_id"));
        });
    }
}
