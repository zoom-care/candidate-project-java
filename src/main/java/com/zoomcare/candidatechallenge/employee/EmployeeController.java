/**
 * 
 */
package com.zoomcare.candidatechallenge.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.employee.Employee;
import com.zoomcare.candidatechallenge.employee.EmployeeService;

/**
 * @author S
 *
 */
@RestController
public class EmployeeController
{

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping("/employees/directreportsincluded")
	public List<Employee> getAllEmployeesWithDirectReports()
	{
		return employeeService.getAllEmployeesWithDirectReports();
	}
	
	@RequestMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable long id)
	{
		return employeeService.getEmployee(id);
	}
	
	@RequestMapping("/employees/count")
	public int getEmployeeCount()
	{
		return employeeService.getEmployeeCount();
	}
}
