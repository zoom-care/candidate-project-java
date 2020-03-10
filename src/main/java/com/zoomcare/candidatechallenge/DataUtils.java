package com.zoomcare.candidatechallenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataUtils {

    private static final String userName = "sa";
    private static final String password = "";
    private static final String url = "jdbc:h2:mem:testdb";

    /**
     * 
     * @return Connection to H2 database
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn = DriverManager.getConnection(url, connectionProps);
        System.out.println("Connected to database");
        return conn;
    }

    // Accessors
    public static String getUrl() {
        return url;
    }
}