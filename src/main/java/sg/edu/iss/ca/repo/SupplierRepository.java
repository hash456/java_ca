package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	public Supplier findSupplierById(int id);
	public Supplier findSupplierByName(String name);
}
