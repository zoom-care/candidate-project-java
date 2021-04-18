package com.zoomcare.candidatechallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import java.math.BigInteger;

//model 
@Entity
@Table(name="EMPLOYEE")
public class Employee
{
    @Id
    @Column
    private BigInteger id;
    //private int id;
    @Column
    private BigInteger supervisor_id;

    public BigInteger get_id()   
    {  
        return id;  
    }  

    public void set_id(BigInteger n){
        id = n;
    }

    public BigInteger get_supervisor_id()   
    {  
        return supervisor_id;  
    }  

    public void set_supervisor_id(BigInteger n){
        supervisor_id = n;
    }

    @Override
	public String toString() {
		return "EmployeeEntry [id=" + id + " supervisor_id=" + supervisor_id + "] \n";
	}

    public static void main(String[] args)
	{
		return;
	}
}