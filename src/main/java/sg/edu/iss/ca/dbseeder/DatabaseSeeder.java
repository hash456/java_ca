package sg.edu.iss.ca.dbseeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Supplier;
import sg.edu.iss.ca.repo.BrandRepository;
import sg.edu.iss.ca.repo.InventoryRepository;
import sg.edu.iss.ca.repo.ProductRepository;
import sg.edu.iss.ca.repo.SupplierRepository;
import sg.edu.iss.ca.service.BrandService;
import sg.edu.iss.ca.service.ProductService;
import sg.edu.iss.ca.service.SupplierService;
import sg.edu.iss.ca.service.BrandImplement;
import sg.edu.iss.ca.service.InventoryImplement;
import sg.edu.iss.ca.service.ProductImplement;

@Component
public class DatabaseSeeder {

	@Autowired
	private ProductService productSvc;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private BrandService brandSvc;
	@Autowired
	private BrandRepository brandRepo;
	@Autowired
	private SupplierService supplierSvc;
	@Autowired
	private SupplierRepository supplierRepo;
	@Autowired
	private InventoryImplement inventorySvc;
	@Autowired
	private InventoryRepository inventoryRepo;
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		resetData();
		seedBrandTable();
		seedProductTable();
		seedSupplierData();
		seedInventoryTable();
	}
	
	private void resetData() {
		productRepo.deleteAll();
		brandRepo.deleteAll();
		supplierRepo.deleteAll();
	}

	private void seedBrandTable() {
		brandSvc.createBrand(new Brand("Ferrari"));
		brandSvc.createBrand(new Brand("Lamborghini"));
		brandSvc.createBrand(new Brand("Maserati"));
	}
	
	private void seedProductTable() {		
		productSvc.createProduct(new Product("1A", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Toyota")));
		productSvc.createProduct(new Product("2A", "Tire", "Car Tire", 
				"Premium Car Tire", "Red", "2x2", 
				"Car", "Tire", new Brand("Honda")));
		productSvc.createProduct(new Product("3A", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Subaru")));
		
		List<Brand> brands = brandSvc.listAllBrands();

		Product p1 = productSvc.createProduct(new Product("1B", "Tyre", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		Product p2 = productSvc.createProduct(new Product("2B", "Tyre", "Car Tire", 
				"Premium Car Tire", "Red", "2x2", 
				"Car", "Tire"));
		Product p3 = productSvc.createProduct(new Product("3B", "Tyre", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		Product p4 = productSvc.createProduct(new Product("3B", "Tyre", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		
		p1.setBrand(brands.get(0));
		p2.setBrand(brands.get(1));
		p3.setBrand(brands.get(2));	
		p4.setBrand(brands.get(0));	
		
		productSvc.updateProduct(p1);
		productSvc.updateProduct(p2);
		productSvc.updateProduct(p3);
		productSvc.updateProduct(p4);

	}
	
	private void seedSupplierData() {
		supplierSvc.createSupplier(new Supplier("tierone@email.com", "tier one"));
		supplierSvc.createSupplier(new Supplier("tiertwo@email.com", "tier two"));
		supplierSvc.createSupplier(new Supplier("tierthree@email.com", "tier three"));
	}
	
	private void seedInventoryTable () {
		inventorySvc.createInventory(new Inventory(36, "A123", 10, 30, 13.0, 15.5, 14.0));
		inventorySvc.createInventory(new Inventory(50, "B394", 40, 15, 20.0, 17.5, 16.0));
		inventorySvc.createInventory(new Inventory(20, "B349", 60, 20, 40.0, 50.5, 45.0));
	}
	
}





