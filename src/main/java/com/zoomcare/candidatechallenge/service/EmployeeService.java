package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.EmployeeWithReports;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeService {

    EmployeeWithReports getById(@PathVariable Long id);

    List<EmployeeWithReports> getAll();
}
