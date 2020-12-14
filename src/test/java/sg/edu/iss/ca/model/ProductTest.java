package sg.edu.iss.ca.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.repo.BrandRepository;
import sg.edu.iss.ca.service.productImplement;

@SpringBootTest
public class ProductTest {

	@Autowired
	private productImplement productSvc;
	
	@Test
	void addProducts() {
		productSvc.addProduct(new Product("a", "b", "c", "d", "e", "f", "g", "h"));
	}
}
