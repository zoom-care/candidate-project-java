package com.zoomcare.candidatechallenge.domain;

import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
public class Employee {
    private Integer id;
    private Integer supervisor_id;
    private List<Property> properties;
    private List<Integer> reports;
}
