package com.zoomcare.candidatechallenge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private Connection connection;

    public EmployeeRepository() throws SQLException {
        this.connection = DataUtils.getConnection();
    }

    @Override
    public EmployeeDO get(int id) throws SQLException {
        EmployeeDO employee = null;
        String query = "SELECT id, supervisor_id FROM employee WHERE id = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                employee = new EmployeeDO(resultSet.getInt("id"), resultSet.getInt("supervisor_id"));
            } else {
                throw new RuntimeException("The specified employee could not be found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getSQLState());
            throw e;
        }

        return employee;
    }

    @Override
    public List<EmployeeDO> getBySupervisor(int supervisorId) throws SQLException {
        List<EmployeeDO> employees = new ArrayList<>();
        String query = "SELECT id, supervisor_id FROM employee WHERE ISNULL(supervisor_id, 0) = " + supervisorId;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employees.add(new EmployeeDO(resultSet.getInt("id"), resultSet.getInt("supervisor_id")));
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getSQLState());
            throw e;
        }

        return employees;
    }

    @Override
    public List<EmployeeDO> getAll() throws SQLException {
        List<EmployeeDO> employees = new ArrayList<>();
        String query = "SELECT id, supervisor_id FROM employee";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employees.add(new EmployeeDO(resultSet.getInt("id"), resultSet.getInt("supervisor_id")));
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getSQLState());
            throw e;
        }

        return employees;
    }

}