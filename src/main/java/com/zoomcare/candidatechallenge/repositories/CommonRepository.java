package com.zoomcare.candidatechallenge.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface CommonRepository {

    default Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
        } catch (SQLException e) {
            System.out.println("Failed to get connection");
            return null;
        }
    }

    default void closeConnection() {
        try {
            this.getConnection().close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection");
        }
    }
}
