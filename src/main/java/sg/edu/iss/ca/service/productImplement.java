package sg.edu.iss.ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.repo.ProductRepository;

@Service
public class productImplement implements Productservice {
	@Autowired
	ProductRepository productrepo;
	
	@Transactional
	public void addProduct(Product product) {
		 productrepo.save(product);
	}
	@Transactional
	public List<Product> listproduct() {
		return productrepo.findAll();
	}	
	@Transactional
	public void deleteProduct(Product product){
		productrepo.delete(product);
	}
	@Transactional
	public Product findProductById(Integer id)
	{
		return productrepo.findProductById(id);
	}
	
}
