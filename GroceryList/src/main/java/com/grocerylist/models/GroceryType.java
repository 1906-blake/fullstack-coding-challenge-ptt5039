package com.grocerylist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="grocery_type")
public class GroceryType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="type_id")
	private int groceryTypeId;
	
	@Column(name="type_name")
	private String groceryTypeName;

	public GroceryType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroceryType(int groceryTypeId, String groceryTypeName) {
		super();
		this.groceryTypeId = groceryTypeId;
		this.groceryTypeName = groceryTypeName;
	}

	public int getGroceryTypeId() {
		return groceryTypeId;
	}

	public void setGroceryTypeId(int groceryTypeId) {
		this.groceryTypeId = groceryTypeId;
	}

	public String getGroceryTypeName() {
		return groceryTypeName;
	}

	public void setGroceryTypeName(String groceryTypeName) {
		this.groceryTypeName = groceryTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groceryTypeId;
		result = prime * result + ((groceryTypeName == null) ? 0 : groceryTypeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroceryType other = (GroceryType) obj;
		if (groceryTypeId != other.groceryTypeId)
			return false;
		if (groceryTypeName == null) {
			if (other.groceryTypeName != null)
				return false;
		} else if (!groceryTypeName.equals(other.groceryTypeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroceryType [groceryTypeId=" + groceryTypeId + ", groceryTypeName=" + groceryTypeName + "]";
	}
	
	
}
