package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Brand;

public interface BrandService {
	public void deleteBrand(Brand brand);
	public List<Brand> listAllBrands();
	
	public Brand createBrand(Brand brand);
	public Brand updateBrand(Brand brand);
	public List<Brand> findBrandByNameLike(String name);
	public Brand findByBrandId(Integer id);
}