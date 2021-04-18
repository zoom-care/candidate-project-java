package com.zoomcare.candidatechallenge.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.zoomcare.candidatechallenge.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
  private JdbcTemplate jdbcTemplate;
  private final String employeeTableName = "EMPLOYEE";
  private final String propertyTableName = "PROPERTY";

  private static final class EmployeeMapper implements RowMapper<Employee> {

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
      Long id = rs.getLong("ID") == 0L ? null : rs.getLong("ID");
      return new Employee(id, new HashMap<String, String>(), new ArrayList<Employee>());
    }
  }

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public List<Employee> getAllSupervisors() {
    String query = "select * from " + employeeTableName + " where SUPERVISOR_ID is null";
    return this.jdbcTemplate.query(query, new EmployeeMapper());
  }

  public List<Employee> getEmployeesBySupervisorId(Long id) {
    String query = "select * from " + employeeTableName + " where SUPERVISOR_ID = ?";
    return this.jdbcTemplate.query(query, new Object[] {id}, new EmployeeMapper());
  }

  public Employee getEmployee(Long id) {
    try {
      String query = "select * from " + employeeTableName + " where ID = ?";
      return this.jdbcTemplate.queryForObject(query, new Object[] {id}, new EmployeeMapper());
    } catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  public List<Map<String, Object>> getEmployeeProperties(Long id) {
    String query = "select * from " + propertyTableName + " where EMPLOYEE_ID = ?";
    return this.jdbcTemplate.queryForList(query, new Object[] {id});
  }
}
