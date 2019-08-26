package com.grocerylist.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocerylist.models.GroceryItem;
import com.grocerylist.models.GroceryList;
import com.grocerylist.services.ListService;

@RestController
@RequestMapping("grocery-lists")
public class ListController {
	@Autowired
	private ListService listService;
	
	@GetMapping
	public Page<GroceryList> findAll(@RequestParam int page, @RequestParam int limit) {
		return listService.findAll(page, limit);
	}
	
	
	@GetMapping("{listId}")
	public GroceryList getOne(@PathVariable int listId) {
		return listService.getOne(listId);
	}
	
	@GetMapping("name/{listName}")
	public List<GroceryList> findByListName(@PathVariable String listName) {
		return listService.findByListName(listName);
	}
	
	@PostMapping
	public GroceryList save(@RequestBody GroceryList newList) {
		return listService.save(newList);
	}
	
	@PostMapping("{listId}/items")
	public GroceryList addItemToList(@PathVariable int listId, @RequestBody GroceryItem item) {
		return listService.addItemToList(listId, item);
	}
	
	@DeleteMapping("{listId}/items/{itemId}")
	public GroceryList removeItemFromList(@PathVariable int listId, @PathVariable int itemId) {
		return listService.removeItemFromList(listId, itemId);
	}
	
	@DeleteMapping
	public void deleteList(@RequestBody GroceryList list) {
		listService.deleteList(list);	
	}
	
}
