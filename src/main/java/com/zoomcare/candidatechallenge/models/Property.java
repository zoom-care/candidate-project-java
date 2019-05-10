package com.zoomcare.candidatechallenge.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROPERTY")
public class Property {

	@EmbeddedId
	private PropertyIdentifier id;

	@Column(name = "VALUE")
	private String value;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	@JsonIgnore
	private Employee employee;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PropertyIdentifier getId() {
		return id;
	}

	public void setId(PropertyIdentifier id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Property))
			return false;
		Property that = (Property) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getValue(), that.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getValue());
	}

}
