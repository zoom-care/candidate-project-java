package com.zoomcare.candidatechallenge;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.*;

@RestController
public class WebController {


    @RequestMapping("/employees")
    public String home() throws SQLException, ClassNotFoundException {
        Statement stmt1 = getStatement();
        Statement stmt2 = getStatement();
        ResultSet results = stmt1.executeQuery("SELECT * FROM employee");
        StringBuilder sb = new StringBuilder();

        while(results.next()) {
            BigDecimal id = results.getBigDecimal("ID");
            sb.append("Employee [id]: ").append(id)
                    .append(" [supervisor_id]: ").append(results.getBigDecimal("SUPERVISOR_ID")).append("\n");

            ResultSet properties = stmt2.executeQuery("SELECT * FROM property WHERE employee_id=" + id);
            while(properties.next()){
                sb.append("  Property [").append(properties.getString("KEY"))
                        .append("]: ").append(properties.getString("VALUE")).append("\n");
            }
            properties.close();
        }
        results.close();
        return sb.toString();
    }

    @RequestMapping("/employee/{id}")
    public String getSpecificEmployee(@PathVariable BigDecimal id) throws SQLException, ClassNotFoundException {
        Statement stmt1 = getStatement();
        Statement stmt2 = getStatement();
        ResultSet results = stmt1.executeQuery("SELECT * FROM employee WHERE id="+ id);
        ResultSet properties = stmt2.executeQuery("SELECT * FROM property WHERE employee_id=" + id);

        StringBuilder sb = new StringBuilder();
        while(results.next()) {
            sb.append("Employee [id]: ").append(id)
                    .append(" [supervisor_id]: ").append(results.getBigDecimal("SUPERVISOR_ID")).append("\n");

            while(properties.next()){
                sb.append("  Property [").append(properties.getString("KEY"))
                        .append("]: ").append(properties.getString("VALUE")).append("\n");
            }
        }
        results.close();
        properties.close();
        return sb.toString();
    }

    public Statement getStatement() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");
        Statement stmt = connection.createStatement();
        return stmt;
    }

}
