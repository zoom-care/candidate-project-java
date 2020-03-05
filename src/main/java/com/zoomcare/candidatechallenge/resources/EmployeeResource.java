package com.zoomcare.candidatechallenge.resources;


import com.zoomcare.candidatechallenge.exception.InvalidDataTypeException;
import com.zoomcare.candidatechallenge.exception.UserDoesNotExistException;
import com.zoomcare.candidatechallenge.model.ClientEmployee;
import com.zoomcare.candidatechallenge.model.db.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;

@Component
@Path("employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@QueryParam("id") String id) throws UserDoesNotExistException, InvalidDataTypeException {
        Employee employee = null;
        BigInteger bigInteger = null;
        try {
            bigInteger = new BigInteger(id);
        } catch (Exception e) {
            throw new InvalidDataTypeException("The data type for id was invalid, it must be a number.");
        }
        employee = employeeService.getEmployeeById(bigInteger);
        return Response.ok(employee).build();
    }

    @GET
    @Path("/toplevel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopLevelEmployees() throws UserDoesNotExistException {
        List<ClientEmployee> employees = employeeService.getTopLevelEmployees();
        return Response.ok(employees).build();
    }
}
