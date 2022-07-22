package com.zoomcare.candidatechallenge.db.repository;

import com.zoomcare.candidatechallenge.db.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

  private static final String FIND_ALL_EMPLOYEES = "SELECT \n" +
      "P.EMPLOYEE_ID  as EMPLOYEE_ID,\n" +
      "E.SUPERVISOR_ID as  SUPERVISOR_ID,\n" +
      "p.VALUE as TITLE,\n" +
      "(select value from PROPERTY where key = 'region' and EMPLOYEE_ID = P.EMPLOYEE_ID ) as REGION\n" +
      "FROM EMPLOYEE  E\n" +
      "left outer join PROPERTY P  \n" +
      "on e.id = p.EMPLOYEE_ID \n" +
      "where P.key = 'title' \n";

  private static final String FIND_EMPLOYEE_BY_ID = FIND_ALL_EMPLOYEES +
      "AND P.EMPLOYEE_ID = ?";

  private final JdbcTemplate jdbcTemplate;

  public Optional<List<Employee>> findAll() {
    return Optional.of(jdbcTemplate.query(FIND_ALL_EMPLOYEES,
        new BeanPropertyRowMapper<>(Employee.class)));
  }

  public Optional<Employee> findByEmployeeId(String employeeId) {
    return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_ID,
        new Object[]{employeeId},
        new BeanPropertyRowMapper<>(Employee.class)));
  }

}
