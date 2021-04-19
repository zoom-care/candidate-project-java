package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*	Author: Riley Everett
	Date: 4/18/2021
	Description: This file contains an implementation of the ZoomCare Candidate Challenge project. The project includes
	a class that acts as a Rest Controller answering requests sent to specific local:8080 addresses. The project
	includes a SQL database containing information about employees and a table linking employees to their supervisors.
	The two main features of this application are to display a nested list of all employees and their direct reports
	and to search for an employee by ID number and display their reports as well. When the server is running the list
	of all employees endpoint is exposed at the address local:8080/orgChart and the search endpoint is exposed
	at local:8080/employeeSearch. The employee search takes an employee_id value that defaults to 1 (the CEO) but can
	be changed by adding ?employee_id=n where n is an integer to the end of the address. For example the Vice President
	of Sales and their reports can be searched by using the address http://localhost:8080/employeeSearch?employee_id=2
	because their employee_id is 2.
*/

@SpringBootApplication
@RestController
public class CandidateChallengeApplication
{

	// Method that processes and answers requests sent to the /employeeSearch extension
	// employee_id can be selected by including the ?employee_id=n where n is and integer to the end of the address
	// otherwise the value is defaulted to 1 (the CEO)
	@GetMapping("employeeSearch")
	public String displayEmployeeSearch(@RequestParam(value = "employee_id", defaultValue = "1") String employee_id) {
		//establish and confirm connection to database
		Connection connection = createConnection();
		assert connection != null;

		// get the details of the employee being searched for and store them in a StringBuilder
		StringBuilder sb = getEmployeeDetails(connection, employee_id);
		// get the details of any direct reports the employee has and add them to the StringBuilder
		sb = getDirectReports(connection, employee_id, sb, 1);

		// close connection to database
		closeConnection(connection);
		// if employee is not in the database display error message otherwise display their data
		if (sb.length() == 0) {
			return "No Employee Found With That ID Number";
		} else {
			return sb.toString();
		}
	}

	// Method that processes and answers request sent to the /orgChart extension
	// returned String is a list of all employees and their direct reports
	@GetMapping("orgChart")
	public String displayOrgChart( ) {
		//establish and confirm connection to database
		Connection connection = createConnection();
		assert connection != null;
		// get the details of the top level employee and store them in a StringBuilder
		StringBuilder sb = (getEmployeeDetails(connection, "1"));
		// get the details of all direct reports to the top level employee and add them to a StringBuilder
		sb = getDirectReports(connection, "1", sb, 1);

		// close connection to database
		closeConnection(connection);
		// return the list of employees and their reports
		return sb.toString();
	}

	// Method that queries the database for information on the employee connected to the employee_id argument
	private StringBuilder getEmployeeDetails(Connection con, String employee_id) {
		// allocate a new string builder for storing database information
		StringBuilder sb = new StringBuilder();
		try {
			//create new statement for queries
			Statement employeeStatement = con.createStatement();
			// query the database for all rows containing the employee's id
			ResultSet results = employeeStatement.executeQuery(
					"SELECT * FROM property WHERE employee_id='" + employee_id + "'");
			// String for storing region data if applicable to employee
			String regionStr = "";
			// process result from query
			while (results.next()) {
				// store region of employee if the row contains that data
				if (results.getString("key").equalsIgnoreCase("region")) {
					regionStr = results.getString("value");
				}
				// add employees title to the string builder if the row contains that data
				if (results.getString("key").equalsIgnoreCase("title")) {
					sb.append(results.getString("value"));
				}
				// if result is on the last row of the ResultSet append region and employee id data to string builder
				if (results.isLast()) {
					if (!regionStr.equals("")) {
						sb.append(", Region: ").append(regionStr);
					}
					sb.append(", ID: ").append(results.getString("employee_id")).append("<br>");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("SQL exception in details display");
		}
		// return formatted data
		return sb;
	}

	// A recursive method that takes as arguments a database connection, employee_id, StringBuilder, and
	// organization layer. The method then recursively adds formatted data from the DB to the sting builder
	// with a depth first search approach for all direct reports to the employee that the id belongs to.
	// once all reports have been added the stringBuilder is returned.
	private StringBuilder getDirectReports(Connection con, String employee_id, StringBuilder sb, int layer) {
		try {
			// create query statements
			Statement reportsStatement = con.createStatement();
			Statement propertyStatement = con.createStatement();
			Statement subStatement = con.createStatement();
			// query the database for all employees that report to the employee
			ResultSet directReports = reportsStatement.executeQuery(
					"SELECT * FROM employee WHERE supervisor_id='" + employee_id + "'");
			// process query data
			while (directReports.next()) {
				// get the employee id of the current report employee
				String currentID = directReports.getString("id");
				// query the database for all rows containing the employee's id
				ResultSet properties = propertyStatement.executeQuery("SELECT * FROM property WHERE employee_id='" + currentID + "'");
				// formatting to help readability
				sb.append(formatHelper(layer));
				// String to store region data if applicable
				String regionStr = "";
				// process result from query
				while (properties.next()) {
					// store region of employee if the row contains that data
					if (properties.getString("key").equalsIgnoreCase("region")) {
						regionStr = properties.getString("value");
					}
					// add employee's title to the string builder if the row contains that data
					if (properties.getString("key").equalsIgnoreCase("title")) {
						sb.append(properties.getString("value"));
					}
					// if result is on the last row of the ResultSet append region and employee id data to string builder
					if (properties.isLast()) {
						if (!regionStr.equals("")) {
							sb.append(", Region: ").append(regionStr);
						}
						sb.append(", ID: ").append(properties.getString("employee_id")).append("<br>");
					}
				}
				// query the database to see if the current employee has any direct reports
				ResultSet subReports = subStatement.executeQuery(
						"SELECT id FROM employee WHERE supervisor_id='" + currentID + "'");
				// if there are direct reports make a recursive call to add their data to the string builder
				if (subReports.next()) {
					sb = getDirectReports(con, currentID, sb, layer+1);
				}
			}
		}
		catch (SQLException e) {
			System.out.println("SQL exception in reports display");
		}
		// return formatted data
		return sb;
	}

	// Method that helps format information from the database to be display properly on the web
	private String formatHelper(int layer) {
		StringBuilder str = new StringBuilder();
		// adds a '>' character for each level deep in the layers of the employee list
		for(int i = 0; i < layer; i++) {
			str.append(">");
		}
		return str.toString();
	}

	// Method that opens a connection to the database and returns it
	private Connection createConnection() {
		try {
			// open connection to h2 database
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		}
		catch (ClassNotFoundException e) {
			System.out.println("class not found");
		}
		catch (SQLException e) {
			System.out.println("SQL exception in connect");
		}
		//return null if connection cannot be established
		return null;
	}

	// Method that closes the argument connection to the database
	private void closeConnection(Connection con) {
		try {
			con.close();
		}
		catch (SQLException e) {
			System.out.println("SQL exception in close");
		}
	}

	public static void main(String[] args)
	{
		// launch spring app
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
