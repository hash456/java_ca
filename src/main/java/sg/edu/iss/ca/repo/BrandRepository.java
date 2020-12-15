package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	public Brand findBrandById(int id);
	public Brand findBrandByName(String name);
	@Query("Select b from Brand b where b.name LIKE CONCAT('%',:name,'%')")
	public List<Brand> findBrandByNameLike(@Param("name") String name);
}
