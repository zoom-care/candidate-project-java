package com.zoomcare.candidatechallenge.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * @author Pabel Lopez
 */

@Repository
public class EmployeeJdbc {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String SELECT_EMPLOYEE_BY_ID ="select * from employee where id=?";
    private String SELECT_CATEGORIES = "select * from property where employee_id=?";
    private String SELECT_REPORT_STRUCTURE = "select id from employee where supervisor_id=?";
    private String SELECT_TOP_EMPLOYEES = "select id from employee where supervisor_id is null";
   
     /** 
      * <p> Return all top level employees</p>
      @return A list wit all top level Employees
     */
    public List <EmployeeBean> getTopLevelEmployees (){
        List<EmployeeBean> topEmpList = new ArrayList<>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(SELECT_TOP_EMPLOYEES);       
        for (int i=0; i<list.size();i++){
            Map<String,Object> topEmployee= list.get(i);             
            EmployeeBean employee = getEmployeeById((Long)topEmployee.get("id")).get(0);
            topEmpList.add(employee);
        }
        return topEmpList;
    }

     /** 
      * <p> Return a specific Employee based on provided id</p>
      @param id The id of the employee to be retrieved
      @return A list of employee properties and nested reporting structure
     */
    public List <EmployeeBean> getEmployeeById(long id){       
        List<Map<String,Object>> list = jdbcTemplate.queryForList(SELECT_EMPLOYEE_BY_ID, id);
        List<EmployeeBean> empList = new ArrayList<>();
              
        list.forEach(item -> {
            List<Map<String,String>> propertyList = new ArrayList<>(); 
            List<List<Map<String,String>>> reportLi;            
            EmployeeBean employee = new EmployeeBean();

            employee.setId((Long)item.get("id"));                   
            propertyList = getProperties(id);
            reportLi = getNestedReport(id);
            if (reportLi.size()>0){
                employee.setReportList(reportLi);
            }
            if (propertyList.size()>0){
                employee.setProperties(propertyList); 
            }                      
            empList.add(employee);
        });
        return empList;
    }

     /** 
      * <p> Return all the properties of one employee based on provided id</p>
      @param id The id of the employee to be retrieved
      @return A list containing the map<key,value> of all properties of employee 
     */
    private List<Map<String,String>> getProperties (long id){
        List<Map<String,String>> properties = new ArrayList<>();
        List<Map<String,Object>> propertiesList = jdbcTemplate.queryForList(SELECT_CATEGORIES, id);

        propertiesList.forEach(property ->{                    
            Map <String,String> map = new HashMap<String,String>();
            map.put("key", (String) property.get("key"));
            map.put("value", (String) property.get("value"));
            properties.add(map);
        });

        return properties;
    }

    /** 
      * <p> Return a list with all nested employee related to the provided supervidor</p>
      @param id The id of the supervisor to get the nested organization
      @return A list containing the map<key,value> of all reported employees of the supervisor provided
     */
    private List<List<Map<String,String>>> getNestedReport (long id){
        List<List<Map<String,String>>> reportList = new ArrayList<List<Map<String,String>>>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(SELECT_REPORT_STRUCTURE, id);
        if (list.size()>0){            
            list.forEach(item ->{
               List<List<Map<String,String>>> innerReportList;
               List<Map<String,String>> subReport = new ArrayList<Map<String,String>>();
               Map <String,String> map = new HashMap<String,String>();
               map.put("supervisor_id", String.valueOf(id));
               map.put("employee_id", String.valueOf(item.get("id")));
               subReport.add(map);
               reportList.add(subReport);
               innerReportList = getNestedReport ((Long)item.get("id"));
               if (innerReportList.size()>0){
                    innerReportList.forEach(sub ->{
                        for (int i=0; i<sub.size();i++){
                            subReport.add(sub.get(i));
                        }
                    });
               }

            });
        }
        return reportList;
    }
}