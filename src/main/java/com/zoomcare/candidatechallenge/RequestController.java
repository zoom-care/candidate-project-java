package com.zoomcare.candidatechallenge;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Controller
public class RequestController {

    @GetMapping("/")
    public String index() {
	return "index";
    }

    /*
      While this method does build the object I want, I am presently unfamiliar with
      Spring's translation of JSON Objects and I wasn't able to get the page to render
      correctly. At the moment it also does not filter by employee, though that can be
      easily added in the stream filter for all employees. Given that the issue is
      primarily a lack of Spring understanding, I've opted to leave this part unfinished
     */
    @GetMapping(path = "/hierarchy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHierarchy(@RequestParam(name = "id") String id, Model model){
	List<Employee> allEmps = findAllEmployeesWithProperties();
	Map<BigInteger, Employee> empMap = new HashMap<>();
	for(Employee emp : allEmps){
	     empMap.put(emp.getId(), emp);
	}

	for(Employee emp : allEmps){
	    if(emp.getSupervisorId() != null)
		empMap.get(emp.getSupervisorId()).getSubEmployees().add(emp);
	}

	List<Employee> topLevelEmployees = allEmps.stream().filter(e -> e.getSupervisorId() == null).collect(Collectors.toList());

	List<JSONObject> responseList = new ArrayList();
	for(Employee emp : topLevelEmployees){
	    responseList.add(buildJsonEmployeeResponse(emp));
	}
	return new ResponseEntity<Object>(responseList, HttpStatus.OK);
    }

    private JSONObject buildJsonEmployeeResponse(Employee emp){
	JSONObject rtv = new JSONObject();
	rtv.put("id", emp.getId());
	rtv.put("supervisorId", emp.getSupervisorId());
	
	JSONArray props = new JSONArray();
	for(Property prop : emp.getProperties()){
	    JSONObject propObj = new JSONObject();
	    propObj.put(prop.getKey(), prop.getValue());
	    props.put(propObj);
	}
	rtv.put("properties", props);

	JSONArray subEmployees = new JSONArray();
	for(Employee subEmp : emp.getSubEmployees()){
	    subEmployees.put(buildJsonEmployeeResponse(subEmp));
	}
	rtv.put("subEmployees", subEmployees);
	return rtv;
    }

    @GetMapping("/allEmployees")
    public String fullEmployeeList(Model model){
	List<Employee> employees = findAllEmployeesWithProperties();
	model.addAttribute("employeeCount", employees.size());
	model.addAttribute("employeeList", employees);
	return "employeeList";
    }

    @GetMapping("/supervisors")
    public String supervisorList(Model model){
	List<Employee> employees = findSupervisorsWithProperties();
	model.addAttribute("employeeCount", employees.size());
	model.addAttribute("employeeList", employees);
	return "employeeList";
    }

    @GetMapping("/employeeById")
    public String employeeById(@RequestParam(name = "id", required = true) String id,  Model model){
	List<Employee> employees = findEmployeeByIdWithProperties(Integer.parseInt(id));//this would need some validation, of course
	model.addAttribute("employeeCount", employees.size());
	model.addAttribute("employeeList", employees);
	return "employeeList";
    }

    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> findAllEmployeesWithProperties(){
	List<Employee> allEmps = findAllEmployees();
	Map<BigInteger, List<Property>> allProps = findAllEmployeeProperties();
	allEmps.stream().forEach(e -> e.setProperties(allProps.get(e.getId())));
	return allEmps;
    }

    public List<Employee> findSupervisorsWithProperties(){
	List<Employee> supervisors = findSupervisors();
	Map<BigInteger, List<Property>> allProps = findAllEmployeeProperties();
	supervisors.stream().forEach(e -> e.setProperties(allProps.get(e.getId())));
	return supervisors;
    }

    public List<Employee> findEmployeeByIdWithProperties(Integer id){
	List<Employee> supervisors = findEmployeeById(id);
	Map<BigInteger, List<Property>> allProps = findAllEmployeeProperties();
	supervisors.stream().forEach(e -> e.setProperties(allProps.get(e.getId())));
	return supervisors;
    }

    public List<Employee> findEmployeeById(Integer id){
	List<Employee> rtv = new ArrayList();
	jdbcTemplate.query(
			   "SELECT id, supervisorId FROM Employee WHERE id = ?",
			   new Object[]{id},
			   (rs, rowNum) -> new Employee(new BigInteger(rs.getString("id")), rs.getString("supervisorId") == null ? null : new BigInteger(rs.getString("supervisorId")))
			   ).forEach(e -> rtv.add(e));
	return rtv;
    }
    
    public List<Employee> findAllEmployees(){
	List<Employee> rtv = new ArrayList();
	jdbcTemplate.query(
			   "SELECT id, supervisorId FROM Employee",
			   (rs, rowNum) -> new Employee(new BigInteger(rs.getString("id")), rs.getString("supervisorId") == null ? null : new BigInteger(rs.getString("supervisorId")))
			   ).forEach(e -> rtv.add(e));
	return rtv;
    }

    public Map<BigInteger, List<Property>> findAllEmployeeProperties(){
	Map<BigInteger, List<Property>> rtv = new HashMap();
	jdbcTemplate.query(
			   "SELECT id, key, value FROM Properties",
			   (rs, rowNum) -> new Property(new BigInteger(rs.getString("id")), rs.getString("key"), rs.getString("value"))
			   ).forEach(p -> {
				   if(!rtv.containsKey(p.getEmployeeId()))
				       rtv.put(p.getEmployeeId(), new ArrayList<>());
				   
				   rtv.get(p.getEmployeeId()).add(p);
			   });
	return rtv;
    }

    public List<Employee> findSupervisors(){
	List<Employee> rtv = new ArrayList();
	jdbcTemplate.query(
			   "SELECT id, supervisorId FROM Employee WHERE supervisorId IS NULL",
			   (rs, rowNum) -> new Employee(new BigInteger(rs.getString("id")), null)
			   ).forEach(e -> rtv.add(e));
	return rtv;
    }
}
