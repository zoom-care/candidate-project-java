package com.zoomcare.candidatechallenge.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;

/*
 * Class to represent an entry in the employees table
 * includes one to many relationship for reports
 * includes one to many relationship for employee properties
 */
@Entity
@Table(name = "EMPLOYEE")
@NamedQuery(name = "Employee.findTopLevelEmployees", 
            query = "Select e from Employee e where e.supervisor_id is null")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Long supervisor_id;

    @OneToMany(mappedBy="employee")
    private List<Property> properties = new ArrayList<>();

    public Employee(){
        super();
    }
/*
    @ManyToOne
    @JoinColumn(name=supervisor_id);
    private Employee supervisor;
*/
    @OneToMany(mappedBy="supervisor_id")
    private List<Employee> reports = new ArrayList<>();

    public Employee(Long id, Long supervisor_id) {
        super();
        this.id = id;
        this.supervisor_id = supervisor_id;
    }

    //@JsonIgnore
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public Long getSupervisorId() {
        return supervisor_id;
    }
    public void setSupervisorId(Long supervisor_id) {
        this.supervisor_id = supervisor_id;
    }
    public List<Employee> getReports() {
        return reports;
    }
    public Map<String,String> getProperties() {
        Map<String, String> propertyMap = new HashMap<>();
	properties.forEach((k)->propertyMap.put(k.getKey(), k.getValue()));
        return propertyMap;
    }
    public void setProperties(List<Property> properties) {
        //shallow copy
        this.properties = properties;
    }
}
