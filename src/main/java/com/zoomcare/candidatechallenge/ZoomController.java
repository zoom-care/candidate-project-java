
package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
//8:42

@RestController
public class ZoomController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    PropertyService propertyService;

    @GetMapping("/employees")
    private String getAllEmployees() {	
	List<Employee> employees = employeeService.getAllEmployees ( );
	List<Property> propertys = propertyService.getAllPropertys ( );
	employees.sort((o1, o2) -> o1.getSupervisorId().compareTo(o2.getSupervisorId()));
	List<Employee> newEmployees = new ArrayList();
	newEmployees.add(employees.get(0));
	List<Employee> finalEmployees = this.sortEmployees(employees, newEmployees);
	return this.getEmployeeHtml(finalEmployees,propertys);
    }
    
    @GetMapping("/employees/{id}")
    private String getEmployee(@PathVariable("id") int id) {
	List<Employee> employees = employeeService.getAllEmployees();
	List<Property> propertys = propertyService.getAllPropertys ( );
	Employee employee = employeeService.getEmployeeById(id);
	List<Employee> newEmployees = new ArrayList();
	newEmployees.add(employee);
	List<Employee> finalEmployees = this.sortEmployees(employees, newEmployees );
	return this.getEmployeeHtml(finalEmployees,propertys);	
    }
    
    private List<Employee> sortEmployees(List<Employee> employees, List<Employee> newEmployees) {
	int newSize = newEmployees.size();
	for ( int i=0 ; i< employees.size(); ++i ) {
	    Employee emp = employees.get(i);	
	    for (int j=0; j< newSize; j++ ) {
		Employee newEmp = employees.get(j);	    
		if (Integer.parseInt(emp.getSupervisorId()) == newEmp.getId()) {    
		    newEmployees.add(j+1,emp);
		} 
	    }
	    newSize = newEmployees.size();
	}
	return newEmployees;
    }
    
    private String getPropertyHTML(List<Property> propertys, int id) {
	String propHTML = "<ul style='color:green'>";
	Iterator propIt = propertys.iterator();
	for (Property newProp : propertys) {
	    if (newProp.getId() == id) {
		propHTML += "<li>" + newProp.getKey() + ": " + newProp.getValue() + "</li>";
	    }
	}
	propHTML += "</ul>";	
	return propHTML;
    }
    
    private String getEmployeeHtml(List<Employee> finalEmployees, List<Property> propertys ) {
	String empHTML = "<ul>";
	for (Employee newEmp : finalEmployees) {
	    empHTML += "<li>EmployeeID: " + newEmp.getId() + " - Supervisor: " + newEmp.getSupervisorId();
	    empHTML += this.getPropertyHTML(propertys, newEmp.getId());
	    empHTML += "</li>";
	} 
	empHTML += "</ul>";
	return empHTML;
    }
}
