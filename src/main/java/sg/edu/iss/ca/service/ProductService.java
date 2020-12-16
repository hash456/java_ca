package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Product;

public interface ProductService {
	public Product createProduct(Product product);
	public Product updateProduct(Product product);
	public void deleteProduct(Product product);
	public List<Product> listAllProducts();
	public Product findProductById(Integer id);
	public List<Product> findProductByNameLike(String name);
	public Product findByProductName(String name);

}
