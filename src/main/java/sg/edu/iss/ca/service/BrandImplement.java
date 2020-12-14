package sg.edu.iss.ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.repo.BrandRepository;

@Service
public class BrandImplement implements BrandService {
	@Autowired
	private BrandRepository brandRepo;
	
	@Transactional
	public Brand addBrand(Brand brand) {
		return brandRepo.save(brand);
	}
	@Transactional
	public void deleteBrand(Brand brand) {
		brandRepo.delete(brand);
	}
	@Transactional
	public List<Brand> listAllBrands(){
		return brandRepo.findAll();
	}
	@Transactional
	public Brand findBrandById(Integer id) {
		return brandRepo.findBrandById(id);
	}

}
