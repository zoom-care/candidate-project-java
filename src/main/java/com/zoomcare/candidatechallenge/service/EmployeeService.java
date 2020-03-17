package com.zoomcare.candidatechallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.mapper.EmployeeRowMapper;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeDBO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ObjectMapper mapper = new ObjectMapper();
    private final String SQL = "select id, supervisorid, employeeid, key, value from employee e left join property p on e.id = p.employeeid";

    /**
     * Get all employees
     *
     * @return A list of all employees with nested hierarchy.
     */
    public String getEmployees(boolean idGiven, int id) {
        JSONObject returnObj = new JSONObject();
        // Get the employees list from the SQL statement and store as a DBO.
        List<EmployeeDBO> employeesDBOList = getEmployeeList();

        // Seed a map to store the employees in a format we can use.
        HashMap<Integer, Employee> employeeMap = new HashMap<>();
        for (EmployeeDBO e : employeesDBOList) {
            Employee employee;
            // If we haven't seen the employee, we create it and initialize a map.
            if (!employeeMap.containsKey(e.getEmployeeId())) {
                employee = new Employee(e.getEmployeeId(), e.getSupervisorId(), e.getKey(), e.getValue());
                employeeMap.put(e.getEmployeeId(), employee);
            } else {
                // Otherwise, obtain the known employee and add the region mapping to the employee.
                employee = employeeMap.get(e.getEmployeeId());
                employee.getTitle().put(e.getKey(), e.getValue());
            }
        }

        // Convert our map to a set so that we can remove any duplication.
        Set<Employee> employeesSet = new LinkedHashSet<>();
        for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
            employeesSet.add(entry.getValue());
        }

        // Create a map which takes the employee id and the set of employees to get the list of direct reports.
        HashMap<Integer, Set<Employee>> allDirectReportsMap = getDirectReports(employeesSet);
        JSONArray returnArr = new JSONArray();

        // Seed the hierarchy.
        for (Employee e : employeesSet) {
            returnArr.put(seedHierarchy(allDirectReportsMap, e, new JSONArray()));
        }

        /*
         * If we are looking up by id, remove the elements we don't need.
         * NOTE: This is a shortcoming of the program, but I ran out of time to
         * clean this up in such a way that it would do the evaluation easier,
         * without evaluating the entire list.
         */
        if (idGiven) {
            for (int i = 0; i < returnArr.length(); i++) {
                JSONArray currArr = returnArr.getJSONArray(i);
                for (int j = 0; j < currArr.length(); j++) {
                    if (currArr.getJSONObject(j).getInt("employeeId") != id) {
                        currArr.remove(j);
                    }
                }
                System.out.println();
            }
        }

        // Clean up empty entries in array.
        for (int i = returnArr.length() - 1; i > 0; i--) {
            JSONArray currObj = returnArr.getJSONArray(i);
            if (currObj.length() == 0) {
                returnArr.remove(i);
            }
        }

        // Return the list of employees as required.
        returnObj.put("content", returnArr);
        return returnObj.toString();
    }

    /**
     * Get employee list.
     *
     * @return The list of employees as a DBO.
     */
    private List<EmployeeDBO> getEmployeeList() {
        return jdbcTemplate.query(SQL, new EmployeeRowMapper());
    }

    /**
     * Get the direct reports for a given employee and store in a map that we can use when seeding.
     *
     * @param employeesList The list of employees
     * @return A map of the direct reports for all employees.
     */
    private HashMap<Integer, Set<Employee>> getDirectReports(Set<Employee> employeesList) {
        HashMap<Integer, Set<Employee>> reportsMap = new HashMap<>();
        // Iterate the list of employees.
        for (Employee e : employeesList) {
            // Create a set so we can remove duplicates.
            Set<Employee> reportsSet = new HashSet<>();
            /*
             * Iterate a second time over the same list so we can compare and see who is
             * the supervisor of who.
             */
            for (Employee e2 : employeesList) {
                if (e.getEmployeeId() == e2.getSupervisorId()) {
                    reportsSet.add(e2);
                }
                // Add to the map for the current employee who their reports are.
                reportsMap.put(e.getEmployeeId(), reportsSet);
            }
        }
        return reportsMap;
    }

    /**
     * Seed the hierarchy (recursive function) to seed the nested hierarchies of employees.
     *
     * @param allDirectReportsMap The map telling us who reports to who.
     * @param employee            The current employee we are seeding
     * @param returnArr           An array representation of all employees in the hierarchy. (This is where the recursion is useful.)
     * @return The hierarchy of a given employee.
     */
    private JSONArray seedHierarchy(HashMap<Integer, Set<Employee>> allDirectReportsMap, Employee employee, JSONArray returnArr) {
        try {
            Set<Employee> directReportsSet = allDirectReportsMap.get(employee.getEmployeeId());
            if (directReportsSet.size() != 0) {
                // Iterate the direct reports and add them to the employee.
                for (Employee directReport : directReportsSet) {
                    employee.getReports().add(directReport);
                    // Recurse to get the subarrays of reports.
                    seedHierarchy(allDirectReportsMap, directReport, returnArr);
                }
                // Add the object to the array.
                JSONObject arrObj = new JSONObject(mapper.writeValueAsString(employee));
                returnArr.put(arrObj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnArr;
    }

    public String findEmployeeById(Integer id) {
        JSONObject returnObj = new JSONObject();

        // Get the employees list from the SQL statement and store as a DBO.
        List<EmployeeDBO> employeesDBOList = getEmployeeList();

        // Seed a map to store the employees in a format we can use.
        HashMap<Integer, Employee> employeeMap = new HashMap<>();
        for (EmployeeDBO e : employeesDBOList) {
            // If it's the employee we want, we will populate it and quit.
            if (e.getEmployeeId() == id) {
                Employee employee;
                // If we haven't seen the employee, we create it and initialize a map.
                if (!employeeMap.containsKey(e.getEmployeeId())) {
                    employee = new Employee(e.getEmployeeId(), e.getSupervisorId(), e.getKey(), e.getValue());
                    employeeMap.put(e.getEmployeeId(), employee);
                } else {
                    // Otherwise, obtain the known employee and add the region mapping to the employee.
                    employee = employeeMap.get(e.getEmployeeId());
                    employee.getTitle().put(e.getKey(), e.getValue());
                }
            }
        }

        // Convert our map to a set so that we can remove any duplication.
        Set<Employee> employeesSet = new LinkedHashSet<>();
        for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
            employeesSet.add(entry.getValue());
        }

        // Create a map which takes the employee id and the set of employees to get the list of direct reports.
        HashMap<Integer, Set<Employee>> allDirectReportsMap = getDirectReports(employeesSet);
        JSONArray returnArr = new JSONArray();

        // Seed the hierarchy.
        for (Employee e : employeesSet) {
            returnArr.put(seedHierarchy(allDirectReportsMap, e, new JSONArray()));
        }

        // Return the list of employees as required.
        returnObj.put("content", returnArr);
        return returnObj.toString();
    }
}