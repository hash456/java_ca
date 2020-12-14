package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Brand;

public interface BrandService {
	public Brand addBrand(Brand brand);
	public void deleteBrand(Brand brand);
	public List<Brand> listAllBrands();
	public Brand findBrandById(Integer id);
}
