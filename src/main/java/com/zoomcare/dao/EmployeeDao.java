package com.zoomcare.dao;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zoomcare.model.Employee;

/**
 * EmployeeDao interacts with the persistance layer to hydrate Employee models
 * It can use anything JPA is compatible with
 */
public class EmployeeDao implements Dao<Employee> {

    // let's use the Spring jdbc template - simplest integration
    private JdbcTemplate jdbcTemplate;
    
    /**
     * EmployeeDao uses a jdbc datasource to talk to persistence
     * DataSource can use any driver that is supported by jdbc
     */
    public EmployeeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // TODO: employee table names and column names should really be a constant someplace
    // TODO: the column names being literals is also kinda gross
    // TODO: not using an ORM makes raw queries look yuck

    @Override
    public Employee get(long id) {
        Employee employee = this.jdbcTemplate.queryForObject("select * from employees where id=?", new Object[]{id},
            new EmployeeMapper());
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = this.jdbcTemplate.query("select * from employees", new EmployeeMapper());
        return list;
    }

    @Override
    public void save(Employee model){
        this.jdbcTemplate.update("insert into employes (supervisor_id) values(?)",
        model.getSupervisorId());
    }

    @Override
    public void update(Employee model) {
        this.jdbcTemplate.update("update employees set supervisorId=?", supervisorId);
    }

    @Override
    public void delete(Employee model) {
        return;
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setSupervisorId(rs.getLong("supervisor_id"));
            return employee;
        }
    }
}