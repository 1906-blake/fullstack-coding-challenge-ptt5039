package com.grocerylist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocerylist.models.GroceryType;
import com.grocerylist.services.TypeService;

@RestController
@RequestMapping("types")
public class TypeController {
	@Autowired
	private TypeService typeService;
	
	@GetMapping
	public List<GroceryType> findAll() {
		return typeService.findAll();
	}
}
