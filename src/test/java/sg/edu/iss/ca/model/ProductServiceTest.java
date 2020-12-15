package sg.edu.iss.ca.model;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.service.BrandService;
import sg.edu.iss.ca.service.ProductService;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productSvc;
	@Autowired
	private BrandService brandSvc;
	
	@Test
	void createProductTest() {
		System.out.println(productSvc.createProduct(
				new Product("100", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Nissan"))));
	}
	
	@Test
	void listAllProductsTest() {
		List<Product> products = productSvc.listAllProducts();
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println(product);
		}
	}
	
	@Test
	void findProductByNameLikeTest() {
		List<Product> products = productSvc.findProductByNameLike("ire");
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println(product);
		}
	}
	
	@Test
	void findProductByIdTest() {
		Product p = productSvc.createProduct(
				new Product("999", "Lexus Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Lexus")));
		System.out.println(productSvc.findProductById(p.getId()));
	}
	
	@Test
	void deleteProductTest() {
		Product p = productSvc.createProduct(
				new Product("666", "Bentley Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire", new Brand("Bentley")));
		productSvc.deleteProduct(p);
	}
	
	@Test
	void updateProductTest() {
		Brand b = brandSvc.createBrand(new Brand("Chevrolet"));
		Product p = productSvc.createProduct(
				new Product("333", "Tire", "Car Tire", 
				"Premium Car Tire", "Black", "2x2", 
				"Car", "Tire"));
		Product p2 = productSvc.createProduct(p);
		p2.setBrand(b);
		p2.setName("Chevorlet Tire");
		System.out.println(productSvc.updateProduct(p2));
	}
	
}
