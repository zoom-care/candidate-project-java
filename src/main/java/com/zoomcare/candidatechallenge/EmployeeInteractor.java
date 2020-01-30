package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import java.math.BigInteger;

public class EmployeeInteractor {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> findAllEmployees(){
	List<Employee> rtv = new ArrayList();
	jdbcTemplate.query(
			   "SELECT id, supervisorId FROM Employee",
			   (rs, rowNum) -> new Employee(new BigInteger(rs.getString("id")), new BigInteger(rs.getString("supervisorId")))
			   )
	    .forEach(e -> rtv.add(e));
	return rtv;
    }
}
