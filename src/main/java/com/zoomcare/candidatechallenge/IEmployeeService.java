package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.dtos.UiEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Defines the operations available for the employee service
 *
 * @author Allen Wickham
 */
@RequestMapping(value = "/employeeservice")
public interface IEmployeeService
{
    /**
     * Obtains all available employees' information.
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    ResponseEntity<List<UiEmployee>> getAllEmployeeInformation();

    /**
     * Obtains the associated employee information for the given ID.
     *
     * @param employeeId The ID of the employee.
     */
    @RequestMapping(value = "/details/{employeeId}", method = RequestMethod.GET)
    ResponseEntity<UiEmployee> getEmployeeInformation(Long employeeId);
}
