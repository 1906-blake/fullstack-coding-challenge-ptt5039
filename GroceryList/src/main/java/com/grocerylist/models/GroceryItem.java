package com.grocerylist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "grocery_item")
@JsonFilter("depth_4")
public class GroceryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int groceryItemId;

	@Column(name = "item_name")
	private String groceryItemName;

	@ManyToOne
	@JoinColumn(name = "item_type")
	private GroceryType groceryItemType;

	@ManyToOne
	@JoinColumn(name = "list")
	private GroceryList groceryItemList;

	public GroceryItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroceryItem(int groceryItemId, String groceryItemName, GroceryType groceryItemType,
			GroceryList groceryItemList) {
		super();
		this.groceryItemId = groceryItemId;
		this.groceryItemName = groceryItemName;
		this.groceryItemType = groceryItemType;
		this.groceryItemList = groceryItemList;
	}

	public int getGroceryItemId() {
		return groceryItemId;
	}

	public void setGroceryItemId(int groceryItemId) {
		this.groceryItemId = groceryItemId;
	}

	public String getGroceryItemName() {
		return groceryItemName;
	}

	public void setGroceryItemName(String groceryItemName) {
		this.groceryItemName = groceryItemName;
	}

	public GroceryType getGroceryItemType() {
		return groceryItemType;
	}

	public void setGroceryItemType(GroceryType groceryItemType) {
		this.groceryItemType = groceryItemType;
	}

	public GroceryList getGroceryItemList() {
		return groceryItemList;
	}

	public void setGroceryItemList(GroceryList groceryItemList) {
		this.groceryItemList = groceryItemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groceryItemId;
		result = prime * result + ((groceryItemList == null) ? 0 : groceryItemList.hashCode());
		result = prime * result + ((groceryItemName == null) ? 0 : groceryItemName.hashCode());
		result = prime * result + ((groceryItemType == null) ? 0 : groceryItemType.hashCode());
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
		GroceryItem other = (GroceryItem) obj;
		if (groceryItemId != other.groceryItemId)
			return false;
		if (groceryItemList == null) {
			if (other.groceryItemList != null)
				return false;
		} else if (!groceryItemList.equals(other.groceryItemList))
			return false;
		if (groceryItemName == null) {
			if (other.groceryItemName != null)
				return false;
		} else if (!groceryItemName.equals(other.groceryItemName))
			return false;
		if (groceryItemType == null) {
			if (other.groceryItemType != null)
				return false;
		} else if (!groceryItemType.equals(other.groceryItemType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroceryItem [groceryItemId=" + groceryItemId + ", groceryItemName=" + groceryItemName
				+ ", groceryItemType=" + groceryItemType + ", groceryItemList=" + groceryItemList + "]";
	}

	
}
