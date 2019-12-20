package com.zoomcare.candidatechallenge.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EmployeeService{
	
	@Path("id/{id}")
	@GET
	EmployeeDto findEmployeeById(@PathParam("id") long id);
	
	@Path("top-level")
	@GET
	List<EmployeeDto> findTopLevelEmployees();
}