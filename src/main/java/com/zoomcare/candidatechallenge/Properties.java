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
@Table(name="PROPERTIES")
public class Properties
{
    @Id
    @Column
    private BigInteger employee_id;
    //private int id;
    @Column
    private String key;

    @Column
    private String value;

    public BigInteger get_employee_id()   
    {  
        return id;  
    }  

    public void set_employee_id(BigInteger n){
        employee_id = n;
    }

    public String get_key(){
        return key;
    }

    public String get_value(){
        return value;
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