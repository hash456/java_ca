package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Brand;

public interface BrandInterface {

	public void createBrand(Brand brand);
	public void updateBrand(Brand brand);
	public void deleteBrand(Brand brand);
	public List<Brand> findBrandByNameLike(String name);
	public List<Brand> listAllBrands();
	public Brand findByBrandId(Integer id);
	
}
