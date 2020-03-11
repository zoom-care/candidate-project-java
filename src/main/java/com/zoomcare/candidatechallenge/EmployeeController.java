package com.zoomcare.candidatechallenge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {
	  @GetMapping("/EMPLOYEE")
	  @ResponseBody
	  public List<Employee> lookupEmployee(@RequestParam(name="id", required=false) String employeeId) {
		  Long employeeIdLong = null;
		  try
		  {
		   employeeIdLong = Long.parseLong(employeeId);
		  }
	  	 catch (NumberFormatException e) {  
	         // Invalid number, return top level employees instead
	      }  
		  final Long innerEmployeeIdLong = employeeIdLong == null ? null : new Long(employeeIdLong);
		  // Top level employees are defined as employees which have a SUPERVISOR_ID value of null.
		  String baseQuery = "SELECT E.ID, E.SUPERVISOR_ID, P.KEY, P.VALUE, DR.ID AS DIRECTREPORT FROM EMPLOYEE E LEFT JOIN EMPLOYEE DR ON E.ID = DR.SUPERVISOR_ID LEFT JOIN PROPERTY P ON E.ID = P.EMPLOYEE_ID WHERE ";
		  String whereClause = (employeeIdLong == null) ? "E.SUPERVISOR_ID IS NULL" : "E.ID = ?";
		  
		  return jdbcTemplate.query(
					        baseQuery.concat(whereClause),
					        new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException {
									if (innerEmployeeIdLong != null)
									{
									ps.setLong(1, innerEmployeeIdLong);
									}
								}},
					        new ResultSetExtractor<List<Employee>>(){
								@Override
								public List<Employee> extractData(ResultSet rs)
										throws SQLException, DataAccessException {
									List<Employee> employeeList = new ArrayList<Employee>();
									Long extEmployeeId = null;
									Long extSupervisorId = null;
									List<Long> extDirectReports = new ArrayList<Long>();
									HashMap<String, String> extKeyValues = new HashMap<String, String>();
									while(rs.next())
									{ 
										if (extEmployeeId != null && extEmployeeId.longValue() != rs.getLong("ID"))
										{
											// create a new Employee pojo from what has been extracted already
											employeeList.add(new Employee(extEmployeeId, extSupervisorId, extDirectReports, extKeyValues));
											extEmployeeId = null;
										}
										if (extEmployeeId == null)
										{
											extEmployeeId = rs.getLong("ID");
											extSupervisorId = rs.getLong("SUPERVISOR_ID");
										}
										
										if (extEmployeeId != null && extEmployeeId.longValue() == rs.getLong("ID") )
										{
											// extract properties and direct reports
											String key = rs.getString("KEY");
											String value = rs.getString("VALUE");
											extKeyValues.put(key, value);
											Long directReport = rs.getLong("DIRECTREPORT");
											extDirectReports.add(directReport);
										}
										if (rs.isLast())
										{
											// create a new Employee pojo from what has been extracted already
											employeeList.add(new Employee(extEmployeeId, extSupervisorId, extDirectReports, extKeyValues));
										}
									}
									return employeeList;
								}    	  
					         });
	  }
	  
	  @Autowired
	  JdbcTemplate jdbcTemplate;
}
