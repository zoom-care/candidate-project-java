
package com.zoomcare.candidatechallenge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
 */

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
