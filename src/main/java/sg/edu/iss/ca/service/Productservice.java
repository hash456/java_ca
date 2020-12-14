package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Product;

public interface Productservice {

	public void addProduct(Product product);
	
	public void deleteProduct(Product product);
	public List<Product> listproduct();
	public Product findProductById(Integer id);
}
