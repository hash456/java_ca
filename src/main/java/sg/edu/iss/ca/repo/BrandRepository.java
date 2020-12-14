package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	public Brand findBrandById(int id);
	public Brand findBrandByName(String name);
}
