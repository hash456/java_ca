package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product findProductById(int id);
	@Query("Select p from Product p where p.name LIKE CONCAT('%',:name,'%')")
	public List<Product> findProductByNameLike(@Param("name") String name);
}
