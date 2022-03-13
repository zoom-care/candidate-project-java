package com.zoomcare.candidatechallenge.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zoomcare.candidatechallenge.objects.Properties;

public class PropertiesRepository implements CommonRepository {
    
    public List<Properties> getListOfPropertiesById(int id) {

        List<Properties> properties = new ArrayList<>();
        
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM PROPERTY WHERE EMPLOYEE_ID = %d", id));
            while (resultSet.next()) {
                properties.add(new Properties(resultSet.getInt("employee_id"), resultSet.getString("key"), resultSet.getString("value")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
        
    }
}
