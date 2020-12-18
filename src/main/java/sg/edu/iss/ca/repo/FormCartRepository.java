package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.FormCart;

public interface FormCartRepository extends JpaRepository<FormCart, Integer> {
	@Query("SELECT fc FROM FormCart fc WHERE fc.id = :id")
	public FormCart findFormCartById(@Param("id") int id);
	
	@Query("SELECT fc FROM FormCart fc WHERE fc.usageForm.id = :id")
	public List<FormCart> findAllByFormId(@Param("id") int id);
	
	@Query("SELECT fc FROM FormCart fc WHERE fc.inventory.id = :id")
	public List<FormCart> findAllByInventoryId(@Param("id") int iid);
}
