package sg.edu.iss.ca.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.repo.BrandRepository;

@SpringBootTest
public class BrandTest {

	@Autowired
	private BrandRepository brandRepo;
	
	@Test
	void addBrands() {
		brandRepo.save(new Brand("YK"));
		brandRepo.save(new Brand("Esther"));
	}
}
