package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDTO;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmployeeDTO> findAllTopLevelEmployees() {

        String sqlQuery = "SELECT * FROM employee WHERE supervisor_id IS NULL OR supervisor_id = 1";
        List<EmployeeDTO> employees = jdbcTemplate.query(sqlQuery, (resultSet, i) -> {
            return new EmployeeDTO(resultSet.getInt("id"),
                    findEmployeePropertiesById(resultSet.getInt("id")),
                    findEmployeeBySupervisorId(resultSet.getInt("id")));
        });

        return employees;
    }

    @Override
    public List<EmployeeDTO> findEmployeeById(Integer id) {

        String sqlQuery = "SELECT * FROM employee WHERE id = ?";
        List<EmployeeDTO> employees = jdbcTemplate.query(sqlQuery, new Object[]{id}, (resultSet, i) -> {
            return new EmployeeDTO(resultSet.getInt("id"),
                    findEmployeePropertiesById(resultSet.getInt("id")),
                    findEmployeeBySupervisorId(resultSet.getInt("id")));
        });

        return employees;
    }

    public List<EmployeePropertyDTO> findEmployeePropertiesById(Integer id) {

        String sqlQuery = "SELECT p.key, p.value FROM property p WHERE employee_id = ?";
        List<EmployeePropertyDTO> employeeProperties = jdbcTemplate.query(sqlQuery, new Object[]{id}, (resultSet, i) -> {
            return new EmployeePropertyDTO(resultSet.getString("key"), resultSet.getString("value"));
        });

        return employeeProperties;
    }

    public List<EmployeeDTO> findEmployeeBySupervisorId(Integer id) {

        String sqlQuery = "SELECT * FROM employee WHERE supervisor_id = ?";
        List<EmployeeDTO> employees = jdbcTemplate.query(sqlQuery, new Object[]{id}, (resultSet, i) -> {
            return new EmployeeDTO(resultSet.getInt("id"),
                    findEmployeePropertiesById(resultSet.getInt("id")),
                    findEmployeeBySupervisorId(resultSet.getInt("id")));
        });

        return employees;
    }

}
