package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Data Access Object class for Employee table
@Repository("Employee")
public class EmployeeDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

private PropertiesDao propertiesDao;

    public EmployeeDao(PropertiesDao propertiesDao, JdbcTemplate jdbcTemplate) {
        this.propertiesDao = propertiesDao;
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String TOP_LEVEL_QUERY = "Select * from employee where supervisor_id is null";

    public List<EmployeeDto> getTopLevelEmployees() {
        List<EmployeeDto> employees = jdbcTemplate.query(TOP_LEVEL_QUERY , ROW_MAPPER_EMP);
        for(EmployeeDto emp:employees) {
            emp.setEmployeeProperties(propertiesDao.getEmployeeProperties(emp.getEmployeeId()));
        }
        for(EmployeeDto emp:employees) {
            emp.setDirectReports(getDirectReports(emp.getEmployeeId()));
        }
        return employees;
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        EmployeeDto emp = new EmployeeDto(employeeId);
        emp.setEmployeeId(employeeId);
        emp.setEmployeeProperties(propertiesDao.getEmployeeProperties(employeeId));
        emp.setDirectReports(getDirectReports(employeeId));
        return emp;
    }

    public List<EmployeeDto> getDirectReports(Long id){
        String DIRECT_REPORTS_QUERY = "Select * from employee where supervisor_id = "+id;
        return jdbcTemplate.query(DIRECT_REPORTS_QUERY, ROW_MAPPER_EMP);
    }

    private static final RowMapper<EmployeeDto> ROW_MAPPER_EMP =
            (rs, rowNum) -> new EmployeeDto(rs.getLong("id"));

}
