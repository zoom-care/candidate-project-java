package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.Models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;

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
       jdbcTemplate = new JdbcTemplate(dataSource);
    };

    public Employee getEmployeeById(Long id) {
        List<EmployeeDTO> employeeDTOList = jdbcTemplate.query(
                // todo: string concat is bad
                "select * from employee inner join property on employee.id = property.employee_id and (supervisor_id = "
                        + id + " or id = " + id + ") order by supervisor_id, id",
                new EmployeeDTOMapper());
        List<Employee> employees = employeeDtoToEmployeeList(employeeDTOList);

        Employee theEmployee = findEmployeeById(employees, id);

        // add reports
        for (Employee e : employees) {
            if (e.getId() != id) {
                theEmployee.getReports().add(e);
            }
        }

        return theEmployee;
    }

    public List<Employee> getAllEmployees() {
        // the objective here is to get all the data we will need in one network call
        List<EmployeeDTO> employeeDTOList = jdbcTemplate.query(
                "select * from employee inner join property on employee.id = property.employee_id order by supervisor_id",
                new EmployeeDTOMapper());
        List<Employee> employees = employeeDtoToEmployeeList(employeeDTOList);

        // add reports
        for (Employee e : employees) {
            if (e.getSupervisorId() != 0) {
                Employee supervisor = findEmployeeById(employees, e.getSupervisorId());
                supervisor.getReports().add(e);
            }
        }

        return employees;
    }

    private Employee findEmployeeById(List<Employee> employees, Long id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    private List<Employee> employeeDtoToEmployeeList(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = new ArrayList<>();

        Long previousId = Long.valueOf(0);
        for (EmployeeDTO dto : employeeDTOList) {
            if (dto.getId() == previousId) {
                // same employee found, update key/value only
                Employee e = findEmployeeById(employees, dto.getId());
                if (e != null) {
                    findEmployeeById(employees, dto.getId())
                            .getEmployeeProperties()
                            .add(new EmployeeProperty(dto.getId(), dto.getKey(), dto.getValue()));
                }
            } else {
                // new employee found
                Employee employee = new Employee(dto.getId(), dto.getSupervisorId());
                List<EmployeeProperty> employeeProperties = new ArrayList<>();
                employeeProperties.add(new EmployeeProperty(dto.getId(), dto.getKey(), dto.getValue()));
                employee.setEmployeeProperties(employeeProperties);
                employees.add(employee);
            }
            previousId = dto.getId();
        }
        return employees;
    }
}
