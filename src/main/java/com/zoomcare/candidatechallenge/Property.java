
package com.zoomcare.candidatechallenge;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Objects;
*/

@Entity
@IdClass(PropertyIdentity.class)
public class Property {

    @Id
    @GeneratedValue    
    private int employee_id;
    @Id
    private String key;
    private String value;

    public int getId(){
	return this.employee_id;
    }
    
    public String getKey() {
	return this.key;
    }

    public String getValue() {
	return this.value;
    }
}
