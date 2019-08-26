package com.grocerylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grocerylist.models.GroceryItem;
import com.grocerylist.repos.ItemRepo;

@Service
public class ItemService {

	@Autowired
	private ItemRepo itemRepo;

	public Page<GroceryItem> findAll(int pageNumber, int offSet) {
		Pageable page = PageRequest.of(pageNumber, offSet);
		return itemRepo.findAll(page);
	}

	public GroceryItem save(GroceryItem newItem) {
		return itemRepo.save(newItem);

	}

}
