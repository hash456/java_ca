package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Inventory;

public interface InventoryService {
	public void deleteInventory(Inventory inventory);
	public List<Inventory> listAllInventories();
	
	public Inventory createInventory(Inventory inventory);
	public Inventory updateInventory(Inventory inventory);
	public List<Inventory> findInventoryByProductName(String name);
	public List<Inventory> findInventoryByProductNameLike(String name);
	public List<Inventory> findInventoryBySupplierName(String name);
	public List<Inventory> findInventoryBySupplierNameLike(String name);
	public Inventory findByInventoryId(Integer id);
}
