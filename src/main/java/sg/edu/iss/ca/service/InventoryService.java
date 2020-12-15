package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Inventory;

public interface InventoryService {
	public void deleteInventory(Inventory inventory);
	public List<Inventory> listAllInventories();
	
	public Inventory createInventory(Inventory inventory);
	public Inventory updateInventory(Inventory inventory);
	public List<Inventory> findInventoryByNameLike(String name);
	public Inventory findByInventoryId(Integer id);
}
