package sg.edu.iss.ca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.repo.InventoryRepository;

@Service
public class InventoryImplement implements InventoryService {
	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Transactional
	public void deleteInventory(Inventory inventory) {
		inventoryRepo.delete(inventory);
	}
	@Transactional
	public List<Inventory> listAllInventories(){
		return inventoryRepo.findAll();
	}

	@Override
	@Transactional
	public Inventory createInventory(Inventory inventory) {
		return inventoryRepo.save(inventory);
	}

	@Override
	@Transactional
	public Inventory updateInventory(Inventory inventory) {
		Inventory i = this.findByInventoryId(inventory.getId());
		if(i != null)
			return inventoryRepo.save(inventory);
		return null;
	}

	@Override
	@Transactional
	public List<Inventory> findInventoryByProductName(String name) {
		return inventoryRepo.findInventoryByProductName(name);
	}

	@Override
	@Transactional
	public List<Inventory> findInventoryByProductNameLike(String name) {
		return inventoryRepo.findInventoryByProductNameLike(name);
	}

	@Override
	@Transactional
	public List<Inventory> findInventoryBySupplierName(String name) {
		return inventoryRepo.findInventoryBySupplierName(name);
	}

	@Override
	@Transactional
	public List<Inventory> findInventoryBySupplierNameLike(String name) {
		return inventoryRepo.findInventoryBySupplierNameLike(name);
	}

	@Override
	@Transactional
	public Inventory findByInventoryId(Integer id) {
		Optional<Inventory> inventoryResponse = inventoryRepo.findById(id);
		if (inventoryResponse.isPresent()) {
			Inventory inventory = inventoryResponse.get();			
			return inventory;
		}
		return null;
	}

}
