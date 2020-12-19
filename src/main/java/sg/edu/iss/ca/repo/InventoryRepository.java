package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	public Inventory findInventoryById(int id);
	
	@Query("select i from Inventory i join i.product p where p.name = :name")
	public List<Inventory> findInventoryByProductName(@Param("name") String name);
	
	@Query("Select i from Inventory i join i.product p where p.name LIKE CONCAT('%',:name,'%')")
	public List<Inventory> findInventoryByProductNameLike(@Param("name") String name);
	
	@Query("select i from Inventory i join i.supplier s where s.name = :name")
	public List<Inventory> findInventoryBySupplierName(@Param("name") String name);
	
	@Query("select i from Inventory i join i.supplier s where i.quantity < i.reorderLvl and s.id=:id")
	public List<Inventory> ReorderReport(@Param("id") int id);
	
	@Query("Select i from Inventory i join i.supplier s where s.name LIKE CONCAT('%',:name,'%')")
	public List<Inventory> findInventoryBySupplierNameLike(@Param("name") String name);
}
