package com.zoomcare.candidatechallenge.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="property")
public class Property implements Serializable {

   private String value;
   @EmbeddedId
   private EmployeePropertyCompositeKey id;
}
