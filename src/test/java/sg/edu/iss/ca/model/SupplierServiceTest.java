package sg.edu.iss.ca.model;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.service.SupplierService;

@SpringBootTest
public class SupplierServiceTest {
	@Autowired
	private SupplierService supplierSvc;
	
	@Test
	void createSupplierTest() {
		System.out.println(supplierSvc.createSupplier(new Supplier("supplierone@email.com", "supplier one")));
	}
	
	@Test
	void listAllSuppliersTest() {
		List<Supplier> suppliers = supplierSvc.listAllSuppliers();
		for (Iterator<Supplier> iterator = suppliers.iterator(); iterator.hasNext();) {
			Supplier brand = (Supplier) iterator.next();
			System.out.println(brand);
		}
	}
	
	@Test
	void findSupplierByNameLikeTest() {
		List<Supplier> suppliers = supplierSvc.findSupplierByNameLike("tie");
		for (Iterator<Supplier> iterator = suppliers.iterator(); iterator.hasNext();) {
			Supplier brand = (Supplier) iterator.next();
			System.out.println(brand);
		}
	}
	
	@Test
	void findSupplierByIdTest() {
		Supplier b = supplierSvc.createSupplier(new Supplier("suppliertwo@gmail.com", "supplier two"));
		Supplier b2 = supplierSvc.findSupplierById(b.getId());
		System.out.println(b2);
	}
	
	@Test
	void deleteSupplierTest() {
		Supplier b = supplierSvc.createSupplier(new Supplier("supplierthree@gmail.com", "supplier three"));
		supplierSvc.deleteSupplier(b);
	}
	
	@Test
	void updateBrandTest() {
		Supplier b = supplierSvc.createSupplier(new Supplier("supplierfour@gmail.com", "supplier four"));
		b.setName("Mercedes Benz");
		System.out.println(supplierSvc.updateSupplier(b));
	}
}
