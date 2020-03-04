package com.zoomcare.candidatechallenge.resources;


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

@Component
@Path("employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@QueryParam("id") BigInteger id) {
        Employee employee = employeeService.getEmployeeById(id);
        return Response.ok(employee).build();
    }

    @GET
    @Path("/toplevel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopLevelEmployees() {
        BigInteger bigInteger = new BigInteger("3");
        BigInteger bigInteger2 = new BigInteger("4");
        Employee employee = new Employee(bigInteger, bigInteger2);
        return Response.ok(employee).build();
    }
}
