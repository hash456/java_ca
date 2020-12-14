package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public void deleteProduct(Product product);
	public List<Product> listAllProducts();
	public Product findProductById(Integer id);
}
