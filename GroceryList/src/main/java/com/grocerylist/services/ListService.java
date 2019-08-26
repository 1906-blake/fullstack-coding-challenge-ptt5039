package com.grocerylist.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grocerylist.models.GroceryItem;
import com.grocerylist.models.GroceryList;
import com.grocerylist.repos.ItemRepo;
import com.grocerylist.repos.ListRepo;

@Service
public class ListService {

	@Autowired
	private ListRepo listRepo;

	@Autowired
	private ItemRepo itemRepo;

	public Page<GroceryList> findAll(int pageNumber, int limit) {
		Pageable page = PageRequest.of(pageNumber, limit);
		return listRepo.findAll(page);
	}

	public GroceryList save(GroceryList newList) {
		return listRepo.save(newList);
	}

	@Transactional
	public GroceryList addItemToList(int listId, GroceryItem item) {
		GroceryItem itemFromDb = itemRepo.getOne(item.getGroceryItemId());
		GroceryList list = listRepo.getOne(listId);
		itemFromDb.setGroceryItemList(list);
		return list;
	}

	@Transactional
	public GroceryList removeItemFromList(int listId, int itemId) {
		GroceryItem item = itemRepo.getOne(itemId);
		item.setGroceryItemList(null);

		GroceryList list = listRepo.getOne(listId);

		return list;
	}

	public void deleteList(GroceryList list) {
		listRepo.delete(list);
	}

	public List<GroceryList> findByListName(String listName) {
		listName = "%" + listName + "%";
		return listRepo.findByGroceryListName(listName);
	}

	public GroceryList getOne(int listId) {
		return listRepo.getOne(listId);
	}

	

}
