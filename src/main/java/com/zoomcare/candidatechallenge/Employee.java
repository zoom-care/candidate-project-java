
package com.zoomcare.candidatechallenge;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String supervisor_id;

    public int getId() {
	return this.id;
    }
    public String getSupervisorId() {
	if (null == this.supervisor_id) {
	    this.supervisor_id = "0";
	}
	return this.supervisor_id;
    }
}
