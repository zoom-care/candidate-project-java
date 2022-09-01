package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.Models.Employee;
import com.zoomcare.candidatechallenge.Models.EmployeeMapper;
import com.zoomcare.candidatechallenge.Models.EmployeeProperty;
import com.zoomcare.candidatechallenge.Models.EmployeePropertyMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/*
The data structure in the database lends itself nicely to JSON objects rather than models
but the JDBC and SQL really don't.  I probably would rather do JSON but my initial
attempts made it appear a bit rougher than using models; if the app were going to contain
many many models, I would push very hard to use JSON instead.
 */
@Repository
public class EmployeeRepository {
    private JdbcTemplate jdbcTemplate;

    EmployeeRepository(DataSource dataSource) {
        // todo: this should live elsewhere; in the model?
       jdbcTemplate = new JdbcTemplate(dataSource);
    };

    public Employee getEmployeeById(Long id) {
        // todo: raw string concat is bad here
        // todo: add employeeproperty list
        Employee employee = jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE WHERE ID = " + id, new EmployeeMapper());
        // todo: create one SQL statement with a join
        employee.setEmployeeProperties(getEmployeePropertiesById(id));
        return employee;
    }

    public List<EmployeeProperty> getEmployeePropertiesById(Long id) {
        // todo: raw string concat is bad
        return jdbcTemplate.query("SELECT * FROM PROPERTY WHERE EMPLOYEE_ID = " + id, new EmployeePropertyMapper());
    }
}
