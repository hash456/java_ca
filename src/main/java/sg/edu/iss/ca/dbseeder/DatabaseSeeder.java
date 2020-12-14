package sg.edu.iss.ca.dbseeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.repo.BrandRepository;
import sg.edu.iss.ca.repo.ProductRepository;
import sg.edu.iss.ca.service.BrandImplement;
import sg.edu.iss.ca.service.ProductImplement;

@Component
public class DatabaseSeeder {

	@Autowired
	private ProductImplement productSvc;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private BrandImplement brandSvc;
	@Autowired
	private BrandRepository brandRepo;
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		resetData();
		seedBrandTable();
		seedProductTable();
	}
	
	private void resetData() {
		productRepo.deleteAll();
		brandRepo.deleteAll();
	}

	private void seedBrandTable() {
		brandSvc.addBrand(new Brand("Ferrari"));
		brandSvc.addBrand(new Brand("Lamborghini"));
		brandSvc.addBrand(new Brand("Maserati"));
	}
	
	private void seedProductTable() {		
		productSvc.addProduct(new Product("1A", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Toyota")));
		productSvc.addProduct(new Product("2A", "Tire", "Car Tire", 
				"Premium Car Tire", "Red", "2x2", 
				"Car", "Tire", new Brand("Honda")));
		productSvc.addProduct(new Product("3A", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Subaru")));
		
		List<Brand> brands = brandSvc.listAllBrands();

		Product p1 = productSvc.addProduct(new Product("1B", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		Product p2 = productSvc.addProduct(new Product("2B", "Tire", "Car Tire", 
				"Premium Car Tire", "Red", "2x2", 
				"Car", "Tire"));
		Product p3 = productSvc.addProduct(new Product("3B", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		p1.setBrand(brands.get(0));
		p2.setBrand(brands.get(1));
		p3.setBrand(brands.get(2));	
		
		productSvc.addProduct(p1);
		productSvc.addProduct(p2);
		productSvc.addProduct(p3);

	}
}





