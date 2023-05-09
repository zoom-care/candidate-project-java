package com.zoomcare.candidatechallenge.model;

import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import com.zoomcare.candidatechallenge.entity.PropertyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
    private Long id;
    private Long supervisor;

    private List<Long> reports = new ArrayList<>();
    private List<PropertyModel> properties = new ArrayList<>();

    public static final EmployeeModel EMPTY = new EmployeeModel();

    public static final EmployeeModel parseFrom(EmployeeEntity entity){
        EmployeeModel model = new EmployeeModel();
        model.setId(entity.getId());

        if(entity.getSupervisor()!= null && entity.getSupervisor().getId()!=null){
            model.setSupervisor(entity.getSupervisor().getId());
        }

        if(entity.getReports()!=null && !entity.getReports().isEmpty()){
            for(EmployeeEntity report : entity.getReports()){
                model.getReports().add(report.getId());
            }
        }

        if(entity.getProperties()!=null && !entity.getProperties().isEmpty()){
            for(PropertyEntity property : entity.getProperties()){
                model.getProperties().add(PropertyModel.parseFrom(property));
            }
        }
        return model;
    }
}
