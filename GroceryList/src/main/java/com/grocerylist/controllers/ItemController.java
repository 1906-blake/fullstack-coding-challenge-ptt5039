package com.grocerylist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocerylist.models.GroceryItem;
import com.grocerylist.services.ItemService;

@RestController
@RequestMapping("grocery-items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@GetMapping
	public Page<GroceryItem> findAll(@RequestParam int page, @RequestParam int limit) {
		return itemService.findAll(page, limit);
	}
	
	@PostMapping
	public GroceryItem save(@RequestBody GroceryItem newItem) {
		return itemService.save(newItem);
	}
}
