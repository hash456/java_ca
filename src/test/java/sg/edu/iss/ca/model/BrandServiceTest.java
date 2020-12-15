package sg.edu.iss.ca.model;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.service.BrandService;

@SpringBootTest
public class BrandServiceTest {

	@Autowired
	private BrandService brandSvc;
	
	@Test
	void createBrandTest() {
		System.out.println(brandSvc.createBrand(new Brand("Proton")));
	}
	
	@Test
	void listAllBrandsTest() {
		List<Brand> brands = brandSvc.listAllBrands();
		for (Iterator<Brand> iterator = brands.iterator(); iterator.hasNext();) {
			Brand brand = (Brand) iterator.next();
			System.out.println(brand);
		}
	}
	
	@Test
	void findBrandByNameLikeTest() {
		List<Brand> brands = brandSvc.findBrandByNameLike("ra");
		for (Iterator<Brand> iterator = brands.iterator(); iterator.hasNext();) {
			Brand brand = (Brand) iterator.next();
			System.out.println(brand);
		}
	}
	
	@Test
	void findBrandByIdTest() {
		Brand b = brandSvc.createBrand(new Brand("BMW"));
		Brand b2 = brandSvc.findByBrandId(b.getId());
		System.out.println(b2);
	}
	
	@Test
	void deleteBrandTest() {
		Brand b = brandSvc.createBrand(new Brand("Audi"));
		brandSvc.deleteBrand(b);
	}
	
	@Test
	void updateBrandTest() {
		Brand b = brandSvc.createBrand(new Brand("Mercedes"));
		b.setName("Mercedes Benz");
		System.out.println(brandSvc.updateBrand(b));
	}
}
