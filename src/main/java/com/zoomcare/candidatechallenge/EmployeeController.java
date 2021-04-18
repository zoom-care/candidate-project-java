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
import com.zoomcare.candidatechallenge.PropertiesRepo;
import com.zoomcare.candidatechallenge.Properties;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;

@RestController
public class EmployeeController{

    final String stars = " ***********************************************************************************************************************************************************************************************************************************************************************************************************************";
    final String line = " ________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________";
    @Autowired
    EmployeeRepo employee_repo;

    @Autowired
    PropertiesRepo prop_repo;

    @RequestMapping(value="/employees")
	public String getAllEmployees() {
		List<Employee> employees= employee_repo.findAll();
        // create map of supervisor_id to list of direct reports
        HashMap<BigInteger,ArrayList<Employee>> super_map= new HashMap();
        // for employees without a supervisor, their supervisor_id is -1
        BigInteger placeholder_id = new BigInteger("-1");
        super_map.put(placeholder_id, new ArrayList<Employee>());
        //List<Employee> no_super_employees  = new ArrayList<Employee>();

        // traverse list of all employees, populate map
        for(int i = 0; i < employees.size(); i++){
            BigInteger super_id = employees.get(i).get_supervisor_id();
            BigInteger employee_id = employees.get(i).get_id();
            // case: employee has a supervisor
            if(employees.get(i).get_supervisor_id() != null){
                // case: employee's supervisor is not in hashmap
                if(super_map.get(super_id) == null){
                    Employee supervisor = employee_repo.findById(super_id).orElse(new Employee());
                    super_map.put(super_id, new ArrayList<Employee>());
                    // fix me: possible concurrency problems with non-atomic transaction on db
                    Employee employee = employee_repo.findById(employee_id).orElse(new Employee());
                    super_map.get(supervisor.get_id()).add(employee);
                }
                // case: employee's supervisor is in hashmap 
                else{
                    Employee employee = employee_repo.findById(employee_id).orElse(new Employee());
                    super_map.get(super_id).add(employee);
                }
            }
            // case: for employees without a supervisor, we assign them to placeholder_supervisor 
            else{
                Employee employee = employee_repo.findById(employee_id).orElse(new Employee());
                super_map.get(placeholder_id).add(employee);
                //no_super_employees.add(employee);
               
            }
        }
        String res = hashmap_printer(super_map);
        // fix me: refactor this into the first traversal of employees
        for (int i = 0; i < employees.size(); i++){
            if(!super_map.containsKey(employees.get(i).get_id())){
                res += stars + " " + employees.get(i).toString();
                res += line;
            }
        }
	 	return res;
    }

    @RequestMapping(value="/employee")  
	private String getEmployee(@RequestParam BigInteger id){
        Employee employee = employee_repo.findById(id).orElseThrow();
        Properties props = prop_repo.findById(id).orElseThrow();
        String res = employee.toString() + "\n " + props.toString();
        return res;
    }
    
    public String hashmap_string_builder(HashMap<BigInteger, ArrayList<Employee>> map, BigInteger id){
        Employee employee = employee_repo.findById(id).orElse(new Employee());
        Properties prop = prop_repo.findById(id).orElse(new Properties());
        String res =""; 
        ArrayList<Employee> list = new ArrayList();
        list = map.get(id);
        if(list == null){
            res += " Employee " + id + " has no reports +++ ";
            return res;
        }
        res += " supervisor: " + employee.toString() + " {BEGIN " + id + "'s reports: " + line ;
        for(int i =0; i < list.size(); i++){
            if(list.get(i).get_id() != null){
                res += " +++" + list.get(i).toString() + "+++  " + hashmap_string_builder(map, list.get(i).get_id());
            } //else{res += " +++ "+ list.get(i).toString();}
        }
        res += " END " + id + "'s reports} " + line;
        return res;
    }
    public String hashmap_printer(HashMap<BigInteger, ArrayList<Employee>> map){
        String res ="";
        BigInteger placeholder_id = new BigInteger("-1");
        for(Map.Entry<BigInteger, ArrayList<Employee>> set : map.entrySet()){
            Employee employee = employee_repo.findById(set.getKey()).orElse(new Employee());
            // fix me : handling employess without supervisor?
            if(employee.get_id() != null){
                res += stars + hashmap_string_builder(map, set.getKey());
                res += line;
            }
        }
        return res;
    }


}