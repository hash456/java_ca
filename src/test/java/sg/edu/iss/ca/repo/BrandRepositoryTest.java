package sg.edu.iss.ca.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.model.Brand;

@SpringBootTest
public class BrandRepositoryTest {
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Test
	void seatData() {
		brandRepo.save(new Brand("Nissan"));
		brandRepo.save(new Brand("Toyota"));
		brandRepo.save(new Brand("Honda"));
		brandRepo.save(new Brand("Michllin"));
		brandRepo.save(new Brand("Yokohomo"));
		brandRepo.save(new Brand("Suzuki"));
	}
	
}
