package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product findProductById(int id);
	public Product findProductByName(String name);
	@Query("Select p from Product p where p.name LIKE CONCAT('%',:name,'%')")
	public List<Product> findProductByNameLike(@Param("name") String name);
	
	@Query("Select p from Product as p where p.name LIKE CONCAT('%',:name,'%')"+" OR p.description LIKE CONCAT('%',:name,'%')"
	         +" OR p.type LIKE CONCAT('%',:name,'%')" + " OR p.color LIKE CONCAT('%',:name,'%')" +" OR p.category LIKE CONCAT('%',:name,'%')"
	 + " OR p.subCategory LIKE CONCAT('%',:name,'%')" + " OR p.partNumber LIKE CONCAT('%',:name,'%')" + " OR p.brand.name LIKE CONCAT('%',:name,'%')"
	         + " Order by p.id"
	 )
	public List<Product> search(@Param("name") String keyword);

	@Query("Select p from Product as p where p.name LIKE CONCAT('%',:name,'%')"+" OR p.description LIKE CONCAT('%',:name,'%')"
	         +" OR p.type LIKE CONCAT('%',:name,'%')" + " OR p.color LIKE CONCAT('%',:name,'%')" +" OR p.category LIKE CONCAT('%',:name,'%')"
	 + " OR p.subCategory LIKE CONCAT('%',:name,'%')" + " OR p.partNumber LIKE CONCAT('%',:name,'%')" + " OR p.brand.name LIKE CONCAT('%',:name,'%')"
	         + " Order by p.id"
	 )
	public Page<Product> searchPageable(@Param("name") String keyword, Pageable pageable);
}
