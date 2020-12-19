package sg.edu.iss.ca.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;

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
	public Inventory findInventoryById(Integer id);
	public Page<Inventory> findPaginated(int pageNo,int pageSize);
	public void restockInventory(Inventory inventory, HttpServletRequest httpServletRequest);
	public void withdrawInventory(Inventory inventory, HttpServletRequest httpServletRequest);
	
	public File ReorderReportGenerate(int id);
}
