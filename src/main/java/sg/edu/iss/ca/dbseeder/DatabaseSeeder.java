package sg.edu.iss.ca.dbseeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Role;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.model.Supplier;
import sg.edu.iss.ca.repo.BrandRepository;
import sg.edu.iss.ca.repo.InventoryRepository;
import sg.edu.iss.ca.repo.ProductRepository;
import sg.edu.iss.ca.repo.StaffRepository;
import sg.edu.iss.ca.repo.SupplierRepository;
import sg.edu.iss.ca.service.BrandService;
import sg.edu.iss.ca.service.ProductService;
import sg.edu.iss.ca.service.SupplierService;
import sg.edu.iss.ca.service.UserService;
import sg.edu.iss.ca.service.InventoryImplement;

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
	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private UserService userSvc;
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		resetData();
		seedBrandTable();
		seedProductTable();
		seedSupplierData();
		seedInventoryTable();
		seedStaffTable();
	}
	
	private void resetData() {
		productRepo.deleteAll();
		brandRepo.deleteAll();
		supplierRepo.deleteAll();
		inventoryRepo.deleteAll();
		staffRepo.deleteAll();
	}

	private void seedBrandTable() {
		brandSvc.createBrand(new Brand("Ferrari trial"));
		brandSvc.createBrand(new Brand("Lamborghini"));
		brandSvc.createBrand(new Brand("Maserati"));
	}
	
	private void seedProductTable() {		
		productSvc.createProduct(new Product("1A", "Tire1", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Toyota")));
		productSvc.createProduct(new Product("2A", "Tire2", "Car Tire", 
				"Premium Car Tire", "Red", "2x2", 
				"Car", "Tire", new Brand("Honda")));
		productSvc.createProduct(new Product("3A", "Tire3", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Subaru")));
		
		List<Brand> brands = brandSvc.listAllBrands();

		Product p1 = productSvc.createProduct(new Product("1B", "Tyre1", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		Product p2 = productSvc.createProduct(new Product("2B", "Tyre2", "Car Tire", 
				"Premium Car Tire", "Red", "2x2", 
				"Car", "Tire"));
		Product p3 = productSvc.createProduct(new Product("3B", "Tyre3", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		Product p4 = productSvc.createProduct(new Product("3B", "Tyre4", "Car Tire", 
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
		
		List<Product> products = productSvc.listAllProducts();
		List<Supplier> suppliers = supplierSvc.listAllSuppliers();
		
		System.out.println(products.get(0).getName());
		System.out.println(suppliers.get(0).getName());
		
		Inventory i1 = inventorySvc.createInventory(new Inventory(38, "A123", 10, 40, 13.0, 16.5, 14.0));
		Inventory i2 = inventorySvc.createInventory(new Inventory(60, "A123", 12, 30, 16.0, 16.0, 14.0));
		Inventory i3 = inventorySvc.createInventory(new Inventory(38, "A123", 11, 50, 13.5, 18.5, 14.0));
		
		i1.setProduct(products.get(0));
		i1.setSupplier(suppliers.get(0));
		
		i2.setProduct(products.get(1));
		i2.setSupplier(suppliers.get(2));
		
		i3.setProduct(products.get(2));
		i3.setSupplier(suppliers.get(0));
		
		inventorySvc.updateInventory(i1);
		inventorySvc.updateInventory(i2);
		inventorySvc.updateInventory(i3);

	}
	
	private void seedStaffTable() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		userSvc.addStaff(new Staff(Role.ADMIN, "Esther", "est", encoder.encode("123456"), true));
		userSvc.addStaff(new Staff(Role.MECHANIC, "Yuen Kwan", "yk", encoder.encode("polymeowphism"), false));
		userSvc.addStaff(new Staff(Role.MECHANIC, "Suria", "sr", encoder.encode("password"), true));
	}
	
}





