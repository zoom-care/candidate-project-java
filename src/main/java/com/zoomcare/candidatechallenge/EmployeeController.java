package com.zoomcare.candidatechallenge;

import java.math.BigInteger;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.zoomcare.candidatechallenge.EmployeeRepo;
import com.zoomcare.candidatechallenge.Employee;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;

@RestController
public class EmployeeController{
    @Autowired
    EmployeeRepo repo;

    // @RequestMapping("/employee")  
	// private ModelAndView getEmployee(@RequestParam int id){
    //     ModelAndView mv = new ModelAndView();
    //     Employee employee = repo.findById(id).orElseThrow();
    //     mv.addObject("employee", employee);
    //     mv.setViewName("getEmployee.jsp");
    //     return mv;

    // }

    // @RequestMapping("/employees")
	// public ModelAndView getAllEmployees() {
	// 	ModelAndView mv= new ModelAndView();
	// 	List<Employee> employees= repo.findAll();
	// 	mv.addObject("employees",employees);
	// 	mv.setViewName("getAllEmployees.jsp");
		
	// 	return mv;
	// }

    @RequestMapping(value="/employee")
	public String getAllEmployees() {
		List<Employee> employees= repo.findAll();

        Map<BigInteger,List<Employee>> super_map= new HashMap();
        // for employees without a supervisor, their supervisor_id is -1
        BigInteger placeholder_id = new BigInteger("-1");
        super_map.put(placeholder_id, new ArrayList<Employee>());
        //List<Employee> no_super_employees  = new ArrayList<Employee>();

        for(int i = 0; i < employees.size(); i++){
            BigInteger super_id = employees.get(i).get_supervisor_id();
            BigInteger employee_id = employees.get(i).get_id();
            // case: employee has a supervisor
            if(employees.get(i).get_supervisor_id() != null){
                // employee's supervisor is not in hashmap
                if(super_map.get(super_id) == null){
                    // fix me: likely problems with going to int value
                    Employee supervisor = repo.findById(super_id).orElse(new Employee());
                    super_map.put(super_id, new ArrayList<Employee>());
                    // fix me: possible concurrency problems with non-atomic transaction on db
                    Employee employee = repo.findById(employee_id).orElse(new Employee());
                    super_map.get(supervisor.get_id()).add(employee);
                } else{
                    Employee employee = repo.findById(employee_id).orElse(new Employee());
                    super_map.get(super_id).add(employee);
                }
            } else{
                // for employees without a supervisor, we assign them to placeholder_supervisor
                Employee employee = repo.findById(employee_id).orElse(new Employee());
                super_map.get(placeholder_id).add(employee);
                //no_super_employees.add(employee);
               
            }
        }
        String res = employees.toString();
        
	 	return res;
    }

    @RequestMapping(value="/employee/{id}")  
	private String getEmployee(@RequestParam BigInteger id){
        Employee employee = repo.findById(id).orElseThrow();
        String res = employee.toString();
        return res;
    }
    

    @RequestMapping(value="/welcome")
	public static String Welcome() {
        return "Welcome to Spring Boot \n";
    }
}