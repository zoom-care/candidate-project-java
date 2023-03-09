package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.service.OrganizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Organization Endpoint
 */
@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Obtain the top-level employees
     * @return
     */
    @GetMapping("top")
    List<EmployeeDTO> getAllTopLevelsEmployees(){
        return organizationService.getAllTopLevelsEmployees();
    }

    /**
     * Obtain employee by ID
     * @param id the employee Identifieer
     * @return the employee information requested.
     */
    @GetMapping("/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id){
        return organizationService.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id));
    }
}
