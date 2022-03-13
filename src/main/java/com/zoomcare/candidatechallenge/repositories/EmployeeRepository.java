package com.zoomcare.candidatechallenge.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zoomcare.candidatechallenge.objects.Employee;

public class EmployeeRepository implements CommonRepository {

    public List<Employee> getListOfEmployees() {

        List<Employee> employees = new ArrayList<>();
        
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("id"), resultSet.getInt("supervisor_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
        
    }

    public Employee getEmployeeById(int id) {
        Employee employee = null;
        
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM EMPLOYEE WHERE ID = %d", id));
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("id"), resultSet.getInt("supervisor_id"));
                break; // only take 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public List<Employee> getListOfTopLevelEmployees() {
        List<Employee> employees = new ArrayList<>();
        
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE WHERE SUPERVISOR_ID IS NULL");
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("id"), resultSet.getInt("supervisor_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public List<Employee> getListOfEmployeesUnderSupervisor(int supervisor_id) {
        List<Employee> employees = new ArrayList<>();
        
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM EMPLOYEE WHERE SUPERVISOR_ID = %d", supervisor_id));
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("id"), resultSet.getInt("supervisor_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getSupervisorByEmployeeId(int id) {
        Employee employee = null;
        
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM EMPLOYEE WHERE ID = (SELECT SUPERVISOR_ID  FROM EMPLOYEE WHERE ID = %d)", id));
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("id"), resultSet.getInt("supervisor_id"));
                break; // only take 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
