package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	public Supplier findSupplierById(int id);
	public Supplier findSupplierByName(String name);
	@Query("Select s from Supplier s where s.name LIKE CONCAT('%',:name,'%')")
	public List<Supplier> findSupplierByNameLike(@Param("name") String name);
}
