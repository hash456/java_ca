package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Supplier;

public interface SupplierService {
	public Supplier createSupplier(Supplier supplier);
	public Supplier updateSupplier(Supplier supplier);
	public void deleteSupplier(Supplier supplier);
	public List<Supplier> listAllSuppliers();
	public Supplier findSupplierById(Integer id);
	public List<Supplier> findSupplierByNameLike(String name);
}
