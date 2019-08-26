package com.grocerylist.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocerylist.models.GroceryItem;

public interface ItemRepo extends JpaRepository<GroceryItem, Integer> {

}
