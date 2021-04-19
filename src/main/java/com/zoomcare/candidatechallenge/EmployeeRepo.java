package com.zoomcare.candidatechallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.zoomcare.candidatechallenge.Employee;
import java.math.BigInteger;

public interface EmployeeRepo extends JpaRepository<Employee, BigInteger>{ 

}  