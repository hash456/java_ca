package sg.edu.iss.ca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.email.RestockMail;
import sg.edu.iss.ca.model.AdminLog;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.repo.InventoryRepository;

@Service
public class InventoryImplement implements InventoryService {
	@Autowired
	private InventoryRepository inventoryRepo;
	@Autowired
	private UserService uSvc;
	@Autowired
	private AdminLogService adminSvc;
	@Autowired
	private MailSenderService senderService;
	
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
	
	@Override
	@Transactional
	public void restockInventory(Inventory inventory, HttpServletRequest httpServletRequest) {
		Inventory i = this.findByInventoryId(inventory.getId());
		if(i != null) {
			i.setQuantity(inventory.getQuantity() + i.getQuantity());
			this.updateInventory(i);
			
			Staff s = uSvc.findStaffByUsername(httpServletRequest.getRemoteUser());
			AdminLog a = new AdminLog(s, i, inventory.getQuantity(), "Restock", LocalDate.now());
			adminSvc.createAdminLog(a);			
		}
	}
	
	@Override
	@Transactional
	public void withdrawInventory(Inventory inventory, HttpServletRequest httpServletRequest) {
		Inventory i = this.findByInventoryId(inventory.getId());
		if(i != null && i.getQuantity() >= inventory.getQuantity()) {
			i.setQuantity(i.getQuantity() - inventory.getQuantity());
			this.updateInventory(i);	
			Staff s = uSvc.findStaffByUsername(httpServletRequest.getRemoteUser());
		
			if(i.getQuantity() <= i.getReorderLvl()) {
				senderService.sendRestockMail(
						new RestockMail(s.getEmail()),
						i.getProduct().getName(),
						i.getSupplier().getName()
						);
			}
			
			AdminLog a = new AdminLog(s, i, inventory.getQuantity(), "Damaged", LocalDate.now());
			adminSvc.createAdminLog(a);	
		}
	}
	
	@Override
	@Transactional
	public Inventory findInventoryById(Integer id) {
		Optional<Inventory> inventoryResponse = inventoryRepo.findById(id);
		if (inventoryResponse.isPresent()) {
			Inventory inventory = inventoryResponse.get();			
			return inventory;
		}
		return null;
	}

}
