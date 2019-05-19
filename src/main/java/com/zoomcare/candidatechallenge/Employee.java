package com.zoomcare.candidatechallenge;
import java.math.BigInteger;
import java.util.ArrayList;

public class Employee {

    private long id;
    private long supervisor_Id;
    private ArrayList<Properties> propertyList = new ArrayList<Properties>();
    private ArrayList<BigInteger> reporteeList = new ArrayList<BigInteger>();

    public Employee(){
        this.id = 0;
    }
    
    public Employee(long id, long val) {
        this.id = id;
        this.supervisor_Id = val;
    }

    public void addProperties(Properties prop){
       this.propertyList.add(prop);
    }

    public void addReporteeList(BigInteger reporteeId){
        this.reporteeList.add(reporteeId);
    }

    public void setEmployeeId(long id){
        this.id = id;
    }

    public void setSuperVisorID(long val){
        this.supervisor_Id = val;
    }

    public long getEmployeeId() {
        return this.id;
    }

    public long getSuperVisorId() {
        return this.supervisor_Id;
    }
    
    public ArrayList<Properties> getPropertyList(){
       return this.propertyList;
    }

    public ArrayList<BigInteger> getReporteeList(){
        return this.reporteeList;
    }
}
