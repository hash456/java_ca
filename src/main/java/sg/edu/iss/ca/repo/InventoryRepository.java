package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Supplier;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	public Inventory findInventoryById(int id);
	
//	@Query("select i from Inventory i join i.product p where p.name = :name")
//	public List<Inventory> findInventoryByProductName(@Param("name") String name);
//	
//	@Query("Select i from Inventory i join i.product p where p.name LIKE CONCAT('%',:name,'%')")
//	public List<Inventory> findInventoryByProductNameLike(@Param("name") String name);
//	
//	@Query("select i from Inventory i join i.supplier s where s.name = :name")
//	public List<Inventory> findInventoryBySupplierName(@Param("name") String name);
//	
	@Query("select i from Inventory i join i.supplier s where i.quantity < i.reorderLvl and s.id=:id")
	public List<Inventory> ReorderReport(@Param("id") int id);
//	
//	@Query("Select i from Inventory i join i.supplier s where s.name LIKE CONCAT('%',:name,'%')")
//	public List<Inventory> findInventoryBySupplierNameLike(@Param("name") String name);
	
	@Query("Select i from Inventory as i where i.product.name LIKE CONCAT('%',:name,'%')"+" OR i.product.description LIKE CONCAT('%',:name,'%')"
	         +" OR i.product.type LIKE CONCAT('%',:name,'%')" + " OR i.product.color LIKE CONCAT('%',:name,'%')" +" OR i.product.category LIKE CONCAT('%',:name,'%')"
	 + " OR i.product.subCategory LIKE CONCAT('%',:name,'%')" + " OR i.product.partNumber LIKE CONCAT('%',:name,'%')" + " OR i.product.brand.name LIKE CONCAT('%',:name,'%')"
	         + " OR i.shelfLocation LIKE CONCAT('%',:name,'%')" + " OR i.supplier.name LIKE CONCAT('%',:name,'%')"
	         +" order by i.id"
	 )
	public List<Inventory> search(@Param("name") String keyword);
	
	@Query("Select i from Inventory as i where i.product.name LIKE CONCAT('%',:name,'%')"+" OR i.product.description LIKE CONCAT('%',:name,'%')"
	         +" OR i.product.type LIKE CONCAT('%',:name,'%')" + " OR i.product.color LIKE CONCAT('%',:name,'%')" +" OR i.product.category LIKE CONCAT('%',:name,'%')"
	 + " OR i.product.subCategory LIKE CONCAT('%',:name,'%')" + " OR i.product.partNumber LIKE CONCAT('%',:name,'%')" + " OR i.product.brand.name LIKE CONCAT('%',:name,'%')"
	         + " OR i.shelfLocation LIKE CONCAT('%',:name,'%')" + " OR i.supplier.name LIKE CONCAT('%',:name,'%')"
	         +" order by i.id"
	 )
	public Page<Inventory> searchPageable(@Param("name") String keyword, Pageable pageable);
}
