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

    String string;


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

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa","")){

            HashMap<String, String> employeeHashMap = new HashMap<>();

            String query = "SELECT * FROM PROPERTY ORDER BY EMPLOYEE_ID";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet propertyResultSet = preparedStatement.executeQuery();

            while (propertyResultSet.next())
            {
                employeeHashMap.put(propertyResultSet.getInt(1)+propertyResultSet.getString(2),propertyResultSet.getString(3));
            }

            query = "SELECT * FROM EMPLOYEE ORDER BY ID";

            preparedStatement = conn.prepareStatement(query);

            ResultSet employeeResultSet = preparedStatement.executeQuery();



            stringBuilder.delete(0,stringBuilder.length());
            Employee currentEmployee;

            while (employeeResultSet.next()) {
                currentEmployee = new Employee(
                        employeeResultSet.getLong(1),    // Employee Id
                        employeeResultSet.getLong(2),     // Supervisor Id
                        employeeHashMap.get(employeeResultSet.getInt(1)+"title"),
                        employeeHashMap.get(employeeResultSet.getInt(1)+"region"));
                employeesById.put(currentEmployee.getEmployeeId(), currentEmployee);
                if(employeesBySupervisor.containsKey(currentEmployee.getSupervisorId())) {
                    employeesBySupervisor.get(currentEmployee.getSupervisorId()).add(currentEmployee);
                } else {
                    ArrayList<Employee> employees = new ArrayList<>();
                    employees.add(currentEmployee);
                    employeesBySupervisor.put(currentEmployee.getSupervisorId(), employees);
                }
            }

            Employee employee = employeesById.get(employeeId);
            addDirectReports(employee, employeesBySupervisor.get(employee.getEmployeeId()));


            string = nestedEmployees(employee);

        } catch (SQLException e)
        {
            System.out.print(e.getMessage());
        }


        return string;
    }

    /**
     * Returns Json representation of DataStructure
     * @param employee employee to be returned with directReports 
     * @return Json representation of hierarchy
     */
    public String nestedEmployees(Employee employee)
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

