package com.zoomcare.candidatechallenge;


import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class EmployeeController {

    HashMap<Long, Employee> employeesById = new HashMap<>();

    HashMap<Long, ArrayList<Employee>> employeesBySupervisor = new HashMap<>();


    @RequestMapping(path="/employees", produces=MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String processForm(@RequestParam(defaultValue="1") String employeeId) {

        return grabEmployeeData(Long.parseLong(employeeId));
    }

    @RequestMapping("/toplevelemployees")
    @ResponseBody
    public String topLevelEmployees()
    {
        return grabEmployeeData(1L);
    }


    /**
     * Grabs employee data from the database and builds data structures
     * @param employeeId the employeeId to set as the top level of the returned Hierarchy
     * @return a Json representation of the specified employee Hierarchy.
     */
    public String grabEmployeeData(Long employeeId)
    {
        employeesBySupervisor.clear();
        employeesById.clear();

        StringBuilder stringBuilder = new StringBuilder();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa","")){

            String query = "SELECT * FROM EMPLOYEE ORDER BY ID";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            stringBuilder.delete(0,stringBuilder.length());

            Employee currentEmployee;

            while (resultSet.next()) {
                currentEmployee = new Employee(
                        resultSet.getLong(1),    // Employee Id
                        resultSet.getLong(2));     // Supervisor Id

                employeesById.put(currentEmployee.getEmployeeId(), currentEmployee);

                if(employeesBySupervisor.containsKey(currentEmployee.getSupervisorId())) {
                    employeesBySupervisor.get(currentEmployee.getSupervisorId()).add(currentEmployee);
                } else {
                    ArrayList<Employee> employees = new ArrayList<>();
                    employees.add(currentEmployee);
                    employeesBySupervisor.put(currentEmployee.getSupervisorId(), employees);
                }
            }


            query = "SELECT * FROM PROPERTY ORDER BY EMPLOYEE_ID";

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                Employee employee = employeesById.get(resultSet.getLong(1)); // Grab employee associated with property entry.
                employee.getProperties().put(resultSet.getString(2), // Key - IE: title, region, etc.
                                            resultSet.getString(3)); // Value
            }

        } catch (SQLException e)
        {
            System.out.print(e.getMessage());
        }

        Employee employee = employeesById.get(employeeId);
        if (employee != null) {
            addDirectReports(employee, employeesBySupervisor.get(employee.getEmployeeId()));
        }

        return employeesToJson(employee);
    }

    /**
     * Returns Json representation of DataStructure
     * @param employee employee to be returned with directReports 
     * @return Json representation of hierarchy
     */
    public String employeesToJson(Employee employee)
    {
        Gson gson = new Gson();
        return gson.toJson(employee);
    }

    /**
     * Adds directReports to the specified employee, and calls recursively to generate nested hierarchy
     * @param currentEmployee employee to be added to
     * @param employees ArrayList of Employees
     */
    public void addDirectReports(Employee currentEmployee, ArrayList<Employee> employees){
        for (Employee employee : employees)
        {
            ArrayList<Employee> directReports = employeesBySupervisor.get(employee.getEmployeeId());
            if (directReports != null && directReports.size() > 0) {
                addDirectReports(employee, directReports);
            }
            currentEmployee.getEmployees().add(employee);
        }
    }

}

