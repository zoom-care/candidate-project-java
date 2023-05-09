package com.zoomcare.candidatechallenge.model;

import com.zoomcare.candidatechallenge.entity.PropertyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyModel {

    private String key;
    private String value;

    public static PropertyModel parseFrom(PropertyEntity entity){
        PropertyModel model = new PropertyModel();
        model.setKey(entity.getId().getKey());
        model.setValue(entity.getValue());
        return model;
    }
}
