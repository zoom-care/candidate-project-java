package com.zoomcare.candidatechallenge.dao;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;

/**
 * EmployeeDao interacts with the persistance layer to hydrate Employee models
 * It can use anything JPA is compatible with
 * 
 * TODO: employee table names and column names should really be a constant someplace
 * TODO: the column names being literals is also kinda gross
 * TODO: not using an ORM as replacing the persistence layer is easier in a DAO
 */
@Repository
public class EmployeeDao implements Dao<Employee> {

    // let's use the Spring jdbc template - simplest integration
    private JdbcTemplate jdbcTemplate;

    public static String EmployeesTable = "employee";
    public static String PropertiesTable = "properties";

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    /**
     * EmployeeDao uses a jdbc datasource to talk to persistence
     * DataSource can use any driver that is supported by jdbc
     */
    public EmployeeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Employee get(long id) {
        Employee employee = this.jdbcTemplate.queryForObject("select * from " + EmployeesTable + " where id=?", new Object[]{id},
            new EmployeeMapper());
        // TODO: retrieve all properties for employee
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = this.jdbcTemplate.query("select * from " + EmployeesTable + "", new EmployeeMapper());
        // TODO: retrieve all properties for employees
        return list;
    }

    @Override
    public Employee save(Employee model){

        KeyHolder keyHolder = new GeneratedKeyHolder();

        // update employees table
        this.jdbcTemplate.update("insert into " + EmployeesTable + " (supervisor_id) values(?)",
        model.getSupervisorId(), keyHolder);
        
        // add all the properties for this employee
        List<Property> employeeProperties = model.getProperties();
        if(employeeProperties != null && employeeProperties.size() > 0) {
            this.jdbcTemplate.batchUpdate("insert into " + PropertiesTable + " (key, value) values(?,?))",
                new PropertyBatcherSetter(employeeProperties, employeeProperties.size())
            );
        }

        // direct reports is not affected here - those are just queried for

        model.setId(keyHolder.getKey().longValue());

        return model;
    }

    @Override
    public int update(Employee model) {
        // return the number of rows affected
        int count = 0;
        // update the supervisorId if necessary
        count += this.jdbcTemplate.update("update " + EmployeesTable + " set supervisorId=? where id=?", model.getSupervisorId(), model.getId());
        // update the properties for this object
        List<Property> employeeProperties = model.getProperties();
        count += this.jdbcTemplate.batchUpdate(
            "update properties set key=?, value=? where employee_id=?",
            new PropertyBatcherSetter(employeeProperties, employeeProperties.size())
        ).length;
        // TODO: refresh direct reports here?
        return count;

    }

    @Override
    public void delete(Employee model) {
        this.jdbcTemplate.update("delete from " + EmployeesTable + " where id = ?", model.getId());
        this.jdbcTemplate.update("delete from " + PropertiesTable + " where employee_id= ?", model.getId());
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setSupervisorId(rs.getLong("supervisor_id"));
            return employee;
        }
    }

    private static final class PropertyBatcherSetter implements BatchPreparedStatementSetter {

        private final List<Property> properties;
        private final int batchSize;

        public PropertyBatcherSetter(List<Property> properties, int batchSize) {
            this.properties = properties;
            this.batchSize = batchSize;
        }
            
        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            Property p = properties.get(i);
            ps.setString(1, p.getKey());
            ps.setString(2, p.getValue());
            ps.setLong(3, p.getEmployeeId());
        }
    
        @Override
        public int getBatchSize() {
            return batchSize;
        }
    }
}