package com.zoomcare.candidatechallenge;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBConnection {

    private final String jdbcUrl = "jdbc:h2:mem:testdb";
    private Connection con;
    private ArrayList<Employee> empArray = new ArrayList<Employee>();
    private String statement = "";
    private PreparedStatement st ;
    private Employee tempEmployee = new Employee();
    private HashMap<BigInteger,Employee> managmentMap = new HashMap<BigInteger,Employee>();

    public DBConnection() throws SQLException {
        this.con = this.Open(jdbcUrl);
    }

    private Connection Open(String url) throws SQLException {
        return DriverManager.getConnection(url,"sa","");
    }

    public void Close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployeeDetails(String id) throws SQLException {
        if(id.length() == 0 || id == ""){
            this.empArray = this.returnTopLevel();
        }else{
            Long bgInt = Long.parseLong(id);
            this.empArray = getEmployee(bgInt);
        }
        this.getEmployeeProperties(this.empArray);
        return this.empArray;
    }

    private ArrayList<Employee> getEmployee(long bigInt) throws SQLException {
        statement = "SELECT * FROM EMPLOYEE where ID = ?";
        st = con.prepareStatement(statement);
        st.setLong(1, bigInt);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            tempEmployee = new Employee();
            tempEmployee.setEmployeeId(rs.getLong(1));
            tempEmployee.setSuperVisorID(rs.getLong(2));
            this.empArray.add(tempEmployee);
        }
        return this.findDirectReportees(this.empArray);
    }

    private ArrayList<Employee> returnTopLevel() throws SQLException {
        HashMap<BigInteger,Employee> tempMap = new HashMap<BigInteger,Employee>();
        ArrayList<Employee> managmentList = new ArrayList<Employee>();
        String statement = "Select * FROM EMPLOYEE";
        PreparedStatement st = con.prepareStatement(statement);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            tempEmployee = new Employee();
            tempEmployee.setEmployeeId(rs.getLong(1));
            tempEmployee.setSuperVisorID(rs.getLong(2));
            this.empArray.add(tempEmployee);
            tempMap.put(BigInteger.valueOf(tempEmployee.getEmployeeId()), tempEmployee);
        }
        for(Employee e : empArray){
            if(tempMap.containsKey(BigInteger.valueOf(e.getSuperVisorId()))){
                if(!managmentMap.containsKey(BigInteger.valueOf(e.getSuperVisorId()))){
                    managmentMap.put(BigInteger.valueOf(e.getSuperVisorId()), e);
                    managmentList.add(tempMap.get(BigInteger.valueOf(e.getSuperVisorId())));
                }
            }
        }
        managmentList = findDirectReportees(managmentList);
        return managmentList;
    }

    public void getEmployeeProperties(ArrayList<Employee> empList){
        Properties property;
        try {
            for(Employee e : empList){
                PreparedStatement st = con.prepareStatement("SELECT * FROM Property where EMPLOYEE_ID = ?");
                st.setLong(1, e.getEmployeeId());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    property  = new Properties();
                    property.setEmployeeId(rs.getLong(1));
                    property.setKey(rs.getString(2));
                    property.setValue(rs.getString(3));
                    e.addProperties(property);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Employee> findDirectReportees(ArrayList<Employee> empList){
        try {
            for(Employee e : empList){
                PreparedStatement st = con.prepareStatement("SELECT * FROM EMPLOYEE where SUPERVISOR_ID = ?");
                st.setLong(1, e.getEmployeeId());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    e.addReporteeList(BigInteger.valueOf(rs.getLong(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empList;
    }
}
