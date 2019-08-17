package com.zoomcare.candidatechallenge;



import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    Connection conn = null;

    public EmployeeDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:testdb","saif","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployeesBySupervisorId(Integer id){

        ArrayList<Employee> employees = null;

        try {
            String query;

            if(id == null) {
                query = "Select ID from EMPLOYEE where SUPERVISOR_ID IS NULL";
            }
            else {
                query = "Select ID from EMPLOYEE where SUPERVISOR_ID ="+id;
            }

            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while(resultSet.next()) {
                if(employees == null){
                    employees = new ArrayList<Employee>();
                }
                employees.add(getEmployeeData(resultSet.getInt(1)));
            }

            if(resultSet != null) {
                resultSet.close();
            }
            if(st != null) {
                st.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return(employees);
    }

    public Employee getEmployeeData(int id) {

        Employee employee = null;

        try {

            String query = "Select * from EMPLOYEE where ID="+id;

            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            if(resultSet.first()) {
                employee = new Employee();
                employee.id = resultSet.getInt(1);
                if (resultSet.getObject(2) != null && !resultSet.wasNull()) {
                    employee.supervisorId = resultSet.getInt(2);
                }
                employee.properties = getPropertiesByEmployeeId(id) ;
                employee.employees = getEmployeesBySupervisorId(id);
            }

            if(resultSet != null) {
                resultSet.close();
            }
            if(st != null) {
                st.close();
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return (employee);
    }

    public ArrayList<Property> getPropertiesByEmployeeId(int id){
        Connection conn = null;

        ArrayList<Property> employeeProperty = null;

        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");

            String query = "Select * from PROPERTY where EMPLOYEE_ID="+id;

            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while(resultSet.next()) {

                if(employeeProperty == null) {
                    employeeProperty = new ArrayList<>();
                }
                Property property = new Property();
                property.key = resultSet.getString(2);
                property.value = resultSet.getString(3);

                employeeProperty.add(property);
            }

            if(resultSet != null) {
                resultSet.close();
            }
            if(st != null) {
                st.close();
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return(employeeProperty);
    }

    public void closeConnection() {
        try {
            if(conn != null) {
                conn.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("BYE");
        }
    }
}
