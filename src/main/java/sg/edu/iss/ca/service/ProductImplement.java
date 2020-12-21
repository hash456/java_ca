package sg.edu.iss.ca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.repo.ProductRepository;

@Service
public class ProductImplement implements ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	@Transactional
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	@Transactional
	public Product updateProduct(Product product) {
		Product p = this.findProductById(product.getId());
		if(p != null)
			return productRepo.save(product);
		return null;
	}
	@Transactional
	public List<Product> listAllProducts() {
		return productRepo.findAll();
	}	
	@Transactional
	public void deleteProduct(Product product){
		productRepo.delete(product);
	}
	@Override
	@Transactional
	public Product findProductById(Integer id) {
		Optional<Product> productResponse = productRepo.findById(id);
		if (productResponse.isPresent()) {
			Product product = productResponse.get();			
			return product;
		}
		return null;
	}
	
	@Transactional
	public List<Product> findProductByNameLike(String name) {
		return productRepo.findProductByNameLike(name);
	}
	
	@Transactional
	public Product findByProductName(String name) {
		Product productResponse = productRepo.findProductByName(name);
		if (productResponse != null) {
			return productResponse;
		}
		return null;
	}
	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize) {
		Pageable pageable= PageRequest.of(pageNo-1, pageSize);
		return productRepo.findAll(pageable);
	}
	@Override
	public List<Product> listAllProducts(String keyword) {
		System.out.println(keyword);
		if(keyword!=null) {
		return productRepo.search(keyword.trim());
		}
		return productRepo.findAll();

	}
	
	

}
