package com.grocerylist.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grocerylist.models.GroceryList;

public interface ListRepo extends JpaRepository<GroceryList, Integer> {
	
	@Query("FROM GroceryList l WHERE LOWER(l.groceryListName) LIKE LOWER(:listName)")
	List<GroceryList> findByGroceryListName(String listName);

}
