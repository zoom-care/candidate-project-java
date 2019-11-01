package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.dtos.UiEmployee;
import com.zoomcare.candidatechallenge.dtos.UiEmployeeDetails;
import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.entities.EmployeeProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * This implements the contract defined in the {@link IEmployeeService} interface.
 *
 * @author Allen Wickham
 */
@Controller
public class EmployeeService implements IEmployeeService
{
    private IEmployeeManager employeeManager;

    @Autowired
    public void setEmployeeManager(IEmployeeManager employeeManager)
    {
        this.employeeManager = employeeManager;
    }

    @Override
    public ResponseEntity<List<UiEmployee>> getAllEmployeeInformation()
    {
        final List<UiEmployee> uiEmployees = new ArrayList<>();

        List<Employee> employeeList = employeeManager.getAllEmployees();

        for (Employee employee: employeeList)
        {
            final UiEmployee uiEmployee = convertEmployee(employee);
            uiEmployees.add(uiEmployee);
        }

        return new ResponseEntity<List<UiEmployee>>(uiEmployees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UiEmployee> getEmployeeInformation(@PathVariable @NotEmpty final Long employeeId)
    {
        final Employee employee = employeeManager.getEmployee(employeeId);
        final UiEmployee uiEmployee = convertEmployee(employee);

        return new ResponseEntity<UiEmployee>(uiEmployee, HttpStatus.OK);
    }

    /**
     * Converts a given {@link Employee} to a {@link UiEmployee}.
     *
     * @param employee The {@link Employee} to convert. Must not be {@code null}.
     *
     * @return a {@link UiEmployee}. Will never be {@code null}.
     */
    protected UiEmployee convertEmployee(Employee employee)
    {
        final UiEmployee uiEmployee = new UiEmployee();

        uiEmployee.setEmployeeId(employee.getId());

        if (employee.getSupervisor() != null)
        {
            uiEmployee.setSupervisor(convertEmployee(employee.getSupervisor()));
        }

        final List<UiEmployeeDetails> uiEmployeeDetailsList = new ArrayList<>();

        for (EmployeeProperty employeeProperties : employee.getEmployeeProperties())
        {
            UiEmployeeDetails uiEmployeeDetails = new UiEmployeeDetails();

            System.out.println("key = " + employeeProperties.getKey() + ", value = " + employeeProperties.getValue());
            uiEmployeeDetails.setKey(employeeProperties.getKey());
            uiEmployeeDetails.setValue(employeeProperties.getValue());

            uiEmployeeDetailsList.add(uiEmployeeDetails);
        }

        uiEmployee.setEmployeeDetails(uiEmployeeDetailsList);
        return uiEmployee;
    }


}
