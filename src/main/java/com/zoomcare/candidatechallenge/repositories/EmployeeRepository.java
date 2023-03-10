package com.zoomcare.candidatechallenge.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.models.Employee;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
     
    //Returns a List of Employees at top level (Haven't a supervisor)
    public List<Employee> getTopLevelEmployees(){
        List<Employee> topLevelEmpList = new ArrayList<>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT id FROM Employee WHERE supervisor_id IS null");
        
        for (int i = 0; i < list.size(); i++){
            Map<String,Object> topEmployee= list.get(i);             
            Employee employee = getEmployeeById((Long)topEmployee.get("id")).get(0);
            topLevelEmpList.add(employee);
        }
        return topLevelEmpList;
    }

    //Returns a specific list of Employees searching by id
    public List<Employee> getEmployeeById(long id){       
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT * FROM Employee WHERE id = ?", id);
        List<Employee> empList = new ArrayList<>();

        list.forEach(item -> {
            List<Map<String,String>> propertyList = new ArrayList<>(); 
            List<List<Map<String,String>>> bySupervisorList;            
            Employee employee = new Employee();

            employee.setId((Long)item.get("id"));                   
            propertyList = getProperties(id);
            bySupervisorList = getEmpBySupervisor(id);
            
            if (bySupervisorList.size() > 0){
                employee.setBySupervisorList(bySupervisorList);
            }
            if (propertyList.size() > 0){
                employee.setProperties(propertyList); 
            }                      
            empList.add(employee);
        });
        
        return empList;
    }

    //Returns a specific list of Properties containing a map<key,value> elements, searching by employee_id
    private List<Map<String,String>> getProperties (long id){
        List<Map<String,String>> propertiesList = new ArrayList<>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT * FROM Property WHERE employee_id = ?", id);

        list.forEach(property ->{                    
            Map <String,String> map = new HashMap<String,String>();
            map.put("key", (String) property.get("key"));
            map.put("value", (String) property.get("value"));
            propertiesList.add(map);
        });

        return propertiesList;
    }

    // Returns a list of Employees containing a map<key,value> elements, searching by supervisor_id
    private List<List<Map<String,String>>> getEmpBySupervisor (long id){
        List<List<Map<String,String>>> bySupervisorList = new ArrayList<List<Map<String,String>>>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT id FROM Employee WHERE supervisor_id = ?", id);
        
        if (list.size()>0){            
            list.forEach(item ->{
               List<List<Map<String,String>>> innerbySupervisorList;
               List<Map<String,String>> nestedReport = new ArrayList<Map<String,String>>();
               Map <String,String> map = new HashMap<String,String>();
               map.put("supervisor_id", String.valueOf(id));
               map.put("employee_id", String.valueOf(item.get("id")));
               nestedReport.add(map);
               bySupervisorList.add(nestedReport);
               innerbySupervisorList = getEmpBySupervisor ((Long)item.get("id"));
               
               if (innerbySupervisorList.size()>0){
                    innerbySupervisorList.forEach(sub ->{
                        for (int i=0; i<sub.size();i++){
                        	nestedReport.add(sub.get(i));
                        }
                    });
               }

            });
        }
        return bySupervisorList;
    }
}
