package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product findProductById(int id);
}
