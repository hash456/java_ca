package sg.edu.iss.ca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.repo.BrandRepository;

@Service
@Transactional
public class BrandImplementation implements BrandInterface {
	
	@Autowired 
	private BrandRepository brandRepo;

	@Override
	public void createBrand(Brand brand) {
		brandRepo.save(brand);
	}

	@Override
	public void updateBrand(Brand brand) {
		brandRepo.save(brand);
	}

	@Override
	public void deleteBrand(Brand brand) {
		brandRepo.delete(brand);
	}

	@Override
	public List<Brand> findBrandByNameLike(String name) {
		return brandRepo.findBrandByNameLike(name);
	}

	@Override
	public List<Brand> listAllBrands() {
		return brandRepo.findAll();
	}

	@Override
	public Brand findByBrandId(Integer id) {
		Optional<Brand> brandResponse = brandRepo.findById(id);
		if (brandResponse.isPresent()) {
			Brand brand = brandResponse.get();			
			return brand;
		}
		return null;
	}

}
