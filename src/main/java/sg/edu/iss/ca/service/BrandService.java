package sg.edu.iss.ca.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Inventory;

public interface BrandService {
	public void deleteBrand(Brand brand);
	public List<Brand> listAllBrands();
	public Brand createBrand(Brand brand);
	public Brand updateBrand(Brand brand);
	public List<Brand> findBrandByNameLike(String name);
	public Brand findByBrandId(Integer id);
	public Brand findByBrandName(String name);
	public Page<Brand> findPaginated(int pageNo,int pageSize);
}
