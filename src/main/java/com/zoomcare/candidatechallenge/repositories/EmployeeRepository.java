package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class EmployeeRepository {
    private JdbcTemplate jdbcTemplate;

    EmployeeRepository(DataSource dataSource) {
       jdbcTemplate = new JdbcTemplate(dataSource);
    };
    public Employee getEmployeeById(Long id) {
        // todo: raw string concat is bad here
        return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE WHERE ID = " + id,
                (result, rowNumber) -> new Employee(result.getString(1), result.getString(2)));
    }
}
