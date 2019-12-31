/**
 * 
 */
package com.zoomcare.candidatechallenge.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.employee.Employee;
import com.zoomcare.candidatechallenge.property.EmployeeProperty;

/**
 * @author S
 * Since this was a small assignment with just two tables I put the code here. In practice there would be a DAO layer containing database access code.
 */
@Service
public class EmployeeService
{
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @return int count of the total number of employees in the organization
	 * This is just a utility method I wrote to test out my database connection, table creation, and test data insertion
	 */
	public int getEmployeeCount()
	{
		int i = jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
		log.info("Total number of employees in the organization is: " + i);
		return i;
	}
	
	/**
	 * 
	 * @return List<Employee> returns a list of all employees in the organization
	 * This is a non-recursive function that returns a flat list of all employees in the organization. It does not fill up the direct reports - that is
	 * done in the getAllEmployeesWithDirectReports method below
	 */
	public List<Employee> getAllEmployees()
	{
		log.info("Getting full non-recursive list of employees");
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Employee> result = new ArrayList<Employee>();
		for (Map<String, Object> row:rows)
		{
			Employee employee = new Employee();
			employee.setId((Long)row.get("id"));
			employee.setSupervisorId((Long)row.get("supervisor_id"));
			employee.setProperties(getPropertiesForEmployee(employee.getId()));
			result.add(employee);
		}
		
		log.info("Completed getting full non-recursive list of employees");
		return result;
	}
	
	/**
	 * 
	 * @return List<Employee> returns a list of all employees in the organization
	 * This function that returns a flat list of all employees in the organization. It fills up the direct reports list recursively for
	 * each employee
	 */
	public List<Employee> getAllEmployeesWithDirectReports()
	{
		log.info("Getting full list of employees with direct reports");
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Employee> result = new ArrayList<Employee>();
		for (Map<String, Object> row:rows)
		{
			Employee employee = new Employee();
			employee.setId((Long)row.get("id"));
			employee.setSupervisorId((Long)row.get("supervisor_id"));
			employee.setProperties(getPropertiesForEmployee(employee.getId()));
			employee = getEmployeeTree(employee);
			result.add(employee);
		}
		
		log.info("Completed getting full list of employees with direct reports");
		return result;
	}
	
	/**
	 * 
	 * @param empId
	 * @return Employee This method returns all the details for an employee including all his direct reports (and their direct reports recursively
	 * all the way down to the leaf node)
	 */
	public Employee getEmployee(long empId)
	{
		log.info("Getting all details for employee id: " + empId);
		String sql = "SELECT * FROM employee WHERE id = ?";
		Employee emp = (Employee)jdbcTemplate.queryForObject(sql, new Object[] {empId}, (rs, rowNum) ->
						new Employee(
								rs.getLong("id"),
								rs.getLong("supervisor_id")
								)
						);
		emp.setProperties(getPropertiesForEmployee(empId));
		
		emp = getEmployeeTree(emp);	
		
		log.info("Completed getting all details for employee id: " + empId);
		return emp;
	}
	
	/**
	 * 
	 * @param employee
	 * @return Employee
	 * This is a recursive method that walks the employee tree and gets the full list of direct reports (and their direct reports in turn all
	 * the way down to the leaf node) for one particular employee
	 */
	private Employee getEmployeeTree(Employee employee)
	{
		List<Employee> directReports = getDirectReports(employee.getId());
		employee.setDirectReports(directReports);
		for (Employee directReport : directReports)
		{
			directReport.setProperties(getPropertiesForEmployee(directReport.getId()));
			getEmployeeTree(directReport);
		}
		
		return employee;
	}
	
	/**
	 * 
	 * @param id
	 * @return List<Employee> This method returns the list of direct reports for one employee whose id is passed in as the input parameter
	 */
	private List<Employee> getDirectReports(long empId)
	{
		log.info("Getting all direct reports for employee id: " + empId);
		String sqlDirectReports = "SELECT * FROM employee WHERE supervisor_id = ?";
		return jdbcTemplate.query(sqlDirectReports, new Object[] {empId}, (rs, rowNum) ->
									new Employee(
											rs.getLong("id"),
											rs.getLong("supervisor_id")
											)
									);
	}
	
	/**
	 * 
	 * @param empId
	 * @return List<EmployeeProperty> List of properties for the input empId parameter
	 * This is a private utility method to return the list of properties for one employee
	 */
	private List<EmployeeProperty> getPropertiesForEmployee(long empId)
	{
		log.info("Getting all properties for employee id: " + empId);
		String sql = "SELECT * FROM property WHERE employee_id = ?";
		return jdbcTemplate.query(sql, new Object[] {empId}, (rs, rowNum) ->
						new EmployeeProperty(
								rs.getLong("employee_id"),
								rs.getString("key"),
								rs.getString("value")
								)
						);
	}
}
