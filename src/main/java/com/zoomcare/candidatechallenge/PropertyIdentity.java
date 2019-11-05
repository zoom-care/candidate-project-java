
package com.zoomcare.candidatechallenge;

import java.io.Serializable;

public class PropertyIdentity implements Serializable {

    private int employee_id;
    private String key;

    public PropertyIdentity() {
    }

    public PropertyIdentity(int id,String key) {
	this.employee_id = id;
	this.key = key;
    }
    public int getEmployeeId() {
	return this.employee_id;
    }

    public String getKey() {
	return this.key;
    }

    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	PropertyIdentity that = (PropertyIdentity) o;
	if(that.employee_id != employee_id ) {
	    return false;
	}
	return key.equals(that.key);
    }

    @Override
    public int hashCode() {	
	int result = 31 * employee_id;
	return result;
    }
}
