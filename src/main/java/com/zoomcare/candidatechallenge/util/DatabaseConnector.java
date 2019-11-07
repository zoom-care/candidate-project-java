package com.zoomcare.candidatechallenge.util;

import java.sql.Connection;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnector {
  private static final DatabaseConnector INSTANCE = new DatabaseConnector();

  private DatabaseConnector(){
  }

  public static DatabaseConnector getInstance(){
    return INSTANCE;
  }

  /**
   * Returns a database connection. Future: Use connection pool.
   *
   * @return Connection
   */
  public Connection getConnection() {
    Connection conn = null;

    try {
      conn = java.sql.DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "SA", "");
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }

    return conn;
  }
}
