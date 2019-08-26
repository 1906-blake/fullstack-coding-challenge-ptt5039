package com.grocerylist.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocerylist.models.GroceryType;

public interface TypeRepo extends JpaRepository<GroceryType, Integer> {

}
