package com.grocerylist.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="grocery_list")
@JsonFilter("depth_4")
public class GroceryList {
	@Id
	@Column(name="list_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groceryListId;
	
	@Column(name="list_name")
	private String groceryListName;
	
	private String description;

	@OneToMany(mappedBy="groceryItemList")
	private List<GroceryItem> items;

	public GroceryList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroceryList(int groceryListId, String groceryListName, String description, List<GroceryItem> items) {
		super();
		this.groceryListId = groceryListId;
		this.groceryListName = groceryListName;
		this.description = description;
		this.items = items;
	}

	public int getGroceryListId() {
		return groceryListId;
	}

	public void setGroceryListId(int groceryListId) {
		this.groceryListId = groceryListId;
	}

	public String getGroceryListName() {
		return groceryListName;
	}

	public void setGroceryListName(String groceryListName) {
		this.groceryListName = groceryListName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<GroceryItem> getItems() {
		return items;
	}

	public void setItems(List<GroceryItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + groceryListId;
		result = prime * result + ((groceryListName == null) ? 0 : groceryListName.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		GroceryList other = (GroceryList) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groceryListId != other.groceryListId)
			return false;
		if (groceryListName == null) {
			if (other.groceryListName != null)
				return false;
		} else if (!groceryListName.equals(other.groceryListName))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroceryList [groceryListId=" + groceryListId + ", groceryListName=" + groceryListName + ", description="
				+ description + ", items=" + items + "]";
	}
	
	
}
