package com.zoomcare.candidatechallenge.repo.impl;

import com.zoomcare.candidatechallenge.repo.EmployeeRepo;
import com.zoomcare.candidatechallenge.domain.Employee;
import com.zoomcare.candidatechallenge.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select employee.*, property.* from employee LEFT OUTER JOIN property ON employee.id=property.employee_id";
        return jdbcTemplate.query(query, new ResultSetExtractor<List<Employee>>() {
            @Override
            public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<Employee> list = new ArrayList<>();
                Map<Integer, Employee> empMap = new HashMap<>();
                Map<Integer, List<Integer>> reportMap = new HashMap<>();
                List<Property> pList = new ArrayList<>();
                while(resultSet.next()){
                    int empId = resultSet.getInt("id");
                    if(!empMap.containsKey(empId)){
                        Employee emp = new Employee();
                        emp.setId(empId);
                        int supervisor_id = resultSet.getInt("supervisor_id");
                        emp.setSupervisor_id(supervisor_id);
                        if(reportMap.containsKey(supervisor_id)){
                            reportMap.get(supervisor_id).add(empId);
                        }else{
                            List<Integer> l = new ArrayList<>();
                            l.add(empId);
                            reportMap.put(supervisor_id, l);
                        }
                        empMap.put(empId, emp);
                    }
                    int empId2 = resultSet.getInt("employee_id");
                    if(empId2>0) {
                        Property property = new Property();
                        property.setEmployee_id(empId2);
                        property.setKey(resultSet.getString("key"));
                        property.setValue(resultSet.getString("value"));
                        if(empMap.get(empId2).getProperties()!=null){
                            empMap.get(empId2).getProperties().add(property);
                        }else{
                            empMap.get(empId2).setProperties(new ArrayList<>());
                            empMap.get(empId2).getProperties().add(property);
                        }
                    }
                }
                list.addAll(empMap.values());
                for (Employee emp:list) {
                    if(emp.getReports()!=null) {
                        if(reportMap.get(emp.getId())!=null)
                        emp.getReports().addAll(reportMap.get(emp.getId()));
                    }else{
                        if(reportMap.get(emp.getId())!=null) {
                            emp.setReports(new ArrayList<>());
                            emp.getReports().addAll(reportMap.get(emp.getId()));
                        }
                    }

                }
                return list;
            }
        });
   }

    @Override
    public Employee getEmployeeById(int id) {
        String query = "select employee.*, property.* from employee LEFT OUTER JOIN property ON employee.id=property.employee_id";
        MapSqlParameterSource j = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(query, new ResultSetExtractor<Employee>() {
            @Override
            public Employee extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Employee employee=null;
                Map<Integer, Integer> supList = new HashMap<>();
                while(resultSet.next()){
                    int empId = resultSet.getInt("id");
                    if(employee==null && empId==id){
                        employee = new Employee();
                        employee.setId(empId);
                        employee.setSupervisor_id(resultSet.getInt("supervisor_id"));
                    }
                    if(resultSet.getInt("supervisor_id")==id){
                        supList.put(empId, empId);
                    }
                    int propId = resultSet.getInt("employee_id");
                    if(propId>0 && empId==id){
                        Property property = new Property();
                        property.setEmployee_id(propId);
                        property.setKey(resultSet.getString("key"));
                        property.setValue(resultSet.getString("value"));
                        if(employee.getProperties()!=null){
                            employee.getProperties().add(property);
                        }else{
                            employee.setProperties(new ArrayList<>());
                            employee.getProperties().add(property);
                        }
                    }
                }
                employee.setReports(new ArrayList<>());
                employee.getReports().addAll(supList.keySet());
                return employee;
            }
        });
    }
}
