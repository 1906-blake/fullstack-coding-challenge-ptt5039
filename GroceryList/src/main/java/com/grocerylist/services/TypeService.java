package com.grocerylist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerylist.models.GroceryType;
import com.grocerylist.repos.TypeRepo;

@Service
public class TypeService {
	@Autowired
	private TypeRepo typeRepo;

	public List<GroceryType> findAll() {
		return typeRepo.findAll();
	}
}
