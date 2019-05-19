package com.zoomcare.candidatechallenge;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private DBConnection dbcon;
    @RequestMapping(value = {"/employee", "/employee/{id}"})
    public ArrayList<Employee> GetEmployee(@PathVariable Optional<String> id) {
        ArrayList<Employee> temp = new ArrayList<Employee>();
        String tempId = "";
        if (id.isPresent()) {
            tempId = id.get();
        } 
        try {
            dbcon = new DBConnection();
            temp = dbcon.getEmployeeDetails(tempId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            dbcon.Close();
        }
        return temp;
    }
}