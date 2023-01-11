package com.zoomcare.candidatechallenge.BO;

import com.zoomcare.candidatechallenge.entity.Property;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class EmployeeBO {
    private Long id ;
    private List<PropertyBO> properties ;
    private Set<EmployeeBO> employeeDirectReportee;

}
