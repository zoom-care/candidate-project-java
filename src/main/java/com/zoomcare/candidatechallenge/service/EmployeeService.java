package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String ALL_EMPLOYEES_SQL = "select id, supervisor_id, key, value from employee e inner join property p on e.id = p.employee_id order by id";
    private final Long TOP_LEVEL_ID = -1L;

    public List<Map<String,Object>> getAllEmployees() {
        List results = jdbcTemplate.queryForList(ALL_EMPLOYEES_SQL);
        return aggregateEmployees(results, null);
    }

    public List<Map<String,Object>> getEmployeesById(Long id) {
        List results = jdbcTemplate.queryForList(ALL_EMPLOYEES_SQL);
        return aggregateEmployees(results, id);
    }

    private List<Map<String,Object>> aggregateEmployees(List results, Long id) {
        Map<String, Object> employee = new HashMap<>();
        Map<String, Object> specified_employee = null;
        Map<Long,List<Map<String,Object>>> employeesBySuper = new HashMap<>();

        for(Object o : results) {
            Map row = (Map)o;
            if(! row.get("id").equals(employee.get("id"))) {
                // new employee
                employee = new HashMap<>();
                Long sid = getSupervisorId(row);
                employee.put("id", row.get("id"));
                employee.put("supervisor", sid);
                employee.put("properties", new HashMap<>());

                if(! employeesBySuper.containsKey(sid)) {
                    employeesBySuper.put(sid, new ArrayList<>());
                }
                employeesBySuper.get(sid).add(employee);
                // if searching for a specific id
                if(id != null && row.get("id").equals(id)) {
                    specified_employee = employee;
                }
            }
            ((HashMap)employee.get("properties")).put(row.get("key"), row.get("value"));
        }

        // if id is specified, return single employee along with its reports
        if(id != null) {
            if (specified_employee != null) {
                // return nested structure of employee with the given id
                getNestedReports(specified_employee, employeesBySuper);
            } else {
                // no employee with that id
                throw new NotFoundException("No employee with id " + id + " found");
            }
            return Collections.singletonList(specified_employee);
        }

        // otherwise return nested structure of entire company
        List<Map<String, Object>> topLevelEmployees = employeesBySuper.get(TOP_LEVEL_ID);
        for (Map<String, Object> topLevelEmployee : topLevelEmployees) {
            getNestedReports(topLevelEmployee, employeesBySuper);
        }
        return topLevelEmployees;
    }

    // starting with a top level employee (no supervisor) recurse through the list to attach all
    // reports in a nested structure
    protected void getNestedReports(Map<String,Object> employee, Map<Long,List<Map<String,Object>>> employeesBySuper) {
        List<Map<String,Object>> reports = employeesBySuper.get(employee.get("id"));
        employee.put("direct_reports", reports);
        // once we've gotten to a leaf in the tree (employee with no reports) the reports list will be null and we are done traversing
        if(reports != null) {
            for (Map<String, Object> report : reports) {
                getNestedReports(report, employeesBySuper);
            }
        }
    }

    // get the employee's supervisor id, or -1 to indicate that the employee has no supervisor
    protected Long getSupervisorId(Map row) {
        Long sid = (Long) row.get("supervisor_id");
        return sid == null ? TOP_LEVEL_ID : sid;
    }

    // this method is not used but I left it here as an example of how to
    // find the list of direct reports for a given employee using streams
    private List<Map> findDirectReports(Long id, List<Map> allEmployees) {
        return allEmployees.stream().filter(e -> e.get("supervisor") != null && e.get("supervisor").equals(id)).collect(Collectors.toList());
    }

}
