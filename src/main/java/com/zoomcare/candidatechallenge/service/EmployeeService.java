package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.datamarshal.EmployeeRowMapper;
import com.zoomcare.candidatechallenge.datamarshal.PropertyRowMapper;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* I am cheating because this is a small DB and the way the tree is represented doesn't allow us
       to reasonably retrieve all the nodes as you go down the child relationships. For a bigger dataset
       we would instead do a fancier DB approach so we could get all children of a
       parent node in a single query, and would do a join or big query with an IN clause to get just the
       relevant properties
    */

    private Map<BigInteger, Employee> getAllEmployees() {
        String sql = "select * from employee";
        List<Employee> emps = jdbcTemplate.query(sql, new EmployeeRowMapper());
        Map<BigInteger, Employee> empMap = new HashMap<>();
        for (Employee emp: emps) {
            empMap.put(emp.getId(), emp);
        }
        for (Employee emp: emps) {
            if (emp.getSupervisorId() != null) {
                empMap.get(emp.getSupervisorId()).addReport(emp);
            }
        }

        // DB table documented as 'PROPERTIES' in table reference but is really 'PROPERTY'!

        String propSql = "select * from property";
        List<Property> props = jdbcTemplate.query(propSql, new PropertyRowMapper());
        for (Property prop: props) {
            Employee theEmp = empMap.get(prop.getEmployeeId());
            theEmp.addProperty(prop);
        }
        return empMap;
    }

    public Employee getById(BigInteger id) {
        Map<BigInteger, Employee> empMap = getAllEmployees();
        return empMap.get(id);
    }

    public List<Employee> getTopLevelList() {
        Map<BigInteger, Employee> empMap = getAllEmployees();
        List<Employee> topLevels = new ArrayList<>();
        for (Employee emp: empMap.values()) {
            if (emp.getSupervisorId() == null) {
                topLevels.add(emp);
            }
        }
        return topLevels;
    }

}
