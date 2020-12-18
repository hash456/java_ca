package sg.edu.iss.ca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Supplier;
import sg.edu.iss.ca.repo.SupplierRepository;

@Service
public class SupplierImplement implements SupplierService{
	@Autowired
	private SupplierRepository supplierRepo;
	
	@Transactional
	public Supplier createSupplier(Supplier supplier) {
		return supplierRepo.save(supplier);
	}
	@Transactional
	public List<Supplier> listAllSuppliers() {
		return supplierRepo.findAll();
	}	
	@Transactional
	public void deleteSupplier(Supplier supplier){
		supplierRepo.delete(supplier);
	}
	@Override
	@Transactional
	public Supplier findSupplierById(Integer id) {
		Optional<Supplier> supplierResponse = supplierRepo.findById(id);
		if (supplierResponse.isPresent()) {
			Supplier supplier = supplierResponse.get();			
			return supplier;
		}
		return null;
	}
	@Transactional
	public List<Supplier> findSupplierByNameLike(String name) {
		return supplierRepo.findSupplierByNameLike(name);
	}
	@Transactional
	public Supplier updateSupplier(Supplier supplier) {
		Supplier s = this.findSupplierById(supplier.getId());
		if(s != null)
			return supplierRepo.save(supplier);
		return null;
	}
	
	@Transactional
	public Supplier findBySupplierName(String name) {
		Supplier supplierResponse = supplierRepo.findSupplierByName(name);
		if (supplierResponse != null) {
			return supplierResponse;
		}
		return null;
	}
	@Override
	public Page<Supplier> findPaginated(int pageNo, int pageSize) {
		Pageable pageable= PageRequest.of(pageNo-1, pageSize);
		return supplierRepo.findAll(pageable);
	}
}
