package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.dtos.UiEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotEmpty;

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
    public ResponseEntity<UiEmployee> getEmployeeInformation(@PathVariable @NotEmpty final String employeeId)
    {
        final UiEmployee employeeDetails = new UiEmployee();

        // TODO: interact with the employeeManager

        return new ResponseEntity<UiEmployee>(employeeDetails, HttpStatus.OK);
    }
}
