package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table
@JsonInclude(Include.NON_NULL)
public class Employee {

    @Id
    @Column
    @ApiModelProperty(notes = "Database id", name = "id", required = true, value = "0")
    @JsonProperty
    private Long id;

    @Column
    @JsonProperty
    @ApiModelProperty(notes = "Database id of Supervising Employee", name = "supervisor id")
    private Long supervisorId;

    @ApiModelProperty(notes = "A list of direct reports to this employee", name = "employees")
    @OneToMany(mappedBy = "supervisorId")
    private Set<Employee> employees;

    @ApiModelProperty(notes = "A list of properties associated with this employee", name = "properties")
    @OneToMany(mappedBy = "propertyKey.employee_Id")
    private Set<Property> properties;
}
