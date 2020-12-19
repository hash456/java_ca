package sg.edu.iss.ca.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
//import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.email.RestockMail;
import sg.edu.iss.ca.model.AdminLog;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.model.Supplier;
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
	@Autowired
	private SupplierService supplierSvc;
	
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
	public Page<Inventory> findPaginated(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable= PageRequest.of(pageNo-1, pageSize);
		return inventoryRepo.findAll(pageable);
	}
	@Override
	public File ReorderReportGenerate(int id) {
		// TODO Auto-generated method stub
		 BufferedWriter bw = null;
		 File file = null;
	      try {
		 List<Inventory> mycontent = inventoryRepo.ReorderReport(id);
	        
		 Supplier s = supplierSvc.findSupplierById(id);
		 LocalDateTime date = LocalDateTime.now();
		 String dateStr = date.toString();
		 
	        // Create a new folder inside the project to store myfile.dat
			Path currentPath = Paths.get(System.getProperty("user.dir"));
			Path filePath = Paths.get(currentPath.toString(), "report", s.getName());
	        if (!Files.exists(filePath)) { 
	            Files.createDirectory(filePath);
	            System.out.println("Directory created");
	        } else {   
	            System.out.println("Directory already exists");
	        }
	       // Create myfile.dat inside the report folder inside the project directory
		 file = new File(Paths.get(filePath.toString(), dateStr +  ".dat").toString());
		 
		 if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file);
		  bw = new BufferedWriter(fw);
		  double total=0;
		  bw.write("\n\n----------------------Inventory Reorder Report for the Supplier-----------------------          \n");
		  bw.write("--------------------------------------------------------------------------------------       \n\n");
		  bw.write("======================================================================================\n");
		  bw.write("\tid\t  quantity\t  ReorderLvl\t ReorderQty\t  UnitPrice\t   Price\n\n");
		  bw.write("======================================================================================\n");
		  for(Inventory i:mycontent)
		  {
		  double price=i.getReorderQty()*i.getWholesalePrice();
		  total+=price;
		  bw.write("\t"+i.getId()+"\t\t"+i.getQuantity()+"\t\t\t"+i.getReorderLvl()+"\t\t\t\t"+i.getReorderQty()+"\t\t\t"+i.getWholesalePrice()+"\t\t" +price+"\n\n");   
		  }
		  bw.write("=======================================================================================\n");
		  bw.write("\t\t\t\t\t\t\t\t\t\t\t\t\t Total:\t\t$"+total);
	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
		finally
		{ 
		   try{
		      if(bw!=null)
			 bw.close();
		      return file;
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
		return file;
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
