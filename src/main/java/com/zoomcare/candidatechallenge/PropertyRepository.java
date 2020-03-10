package com.zoomcare.candidatechallenge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PropertyRepository implements IPropertyRepository {

    private Connection connection;

    public PropertyRepository() throws SQLException {
        this.connection = DataUtils.getConnection();
    }

    @Override
    public List<PropertyDO> getByEmployee(int employeeId) throws Exception {
        List<PropertyDO> properties = new ArrayList<>();
        String query = "SELECT employee_id, key, value FROM property WHERE employee_id = " + employeeId;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                properties.add(new PropertyDO(resultSet.getInt("employee_id"), resultSet.getString("key"), resultSet.getString("value")));
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getSQLState());
            throw e;
        }

        return properties;
    }
}