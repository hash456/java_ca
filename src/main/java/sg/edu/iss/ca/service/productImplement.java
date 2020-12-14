package sg.edu.iss.ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.repo.ProductRepository;

@Service
public class ProductImplement implements ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	@Transactional
	public void addProduct(Product product) {
		 productRepo.save(product);
	}
	@Transactional
	public List<Product> listAllProducts() {
		return productRepo.findAll();
	}	
	@Transactional
	public void deleteProduct(Product product){
		productRepo.delete(product);
	}
	@Transactional
	public Product findProductById(Integer id)
	{
		return productRepo.findProductById(id);
	}
	
}
