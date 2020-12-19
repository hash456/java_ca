package sg.edu.iss.ca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.repo.BrandRepository;

@Service
public class BrandImplement implements BrandService {
	@Autowired
	private BrandRepository brandRepo;
	
	@Transactional
	public void deleteBrand(Brand brand) {
		brandRepo.delete(brand);
	}
	@Transactional
	public List<Brand> listAllBrands(){
		return brandRepo.findAll();
	}

	@Override
	@Transactional
	public Brand createBrand(Brand brand) {
		return brandRepo.save(brand);
	}

	@Override
	@Transactional
	public Brand updateBrand(Brand brand) {
		Brand b = this.findByBrandId(brand.getId());
		if(b != null)
			return brandRepo.save(brand);
		return null;
	}

	@Override
	@Transactional
	public List<Brand> findBrandByNameLike(String name) {
		return brandRepo.findBrandByNameLike(name);
	}


	@Override
	@Transactional
	public Brand findByBrandId(Integer id) {
		Optional<Brand> brandResponse = brandRepo.findById(id);
		if (brandResponse.isPresent()) {
			Brand brand = brandResponse.get();			
			return brand;
		}
		return null;
	}
	
	@Transactional
	public Brand findByBrandName(String name) {
		Brand brandResponse = brandRepo.findBrandByName(name);
		if (brandResponse != null) {
			return brandResponse;
		}
		return null;
	}
	@Override
	public Page<Brand> findPaginated(int pageNo, int pageSize) {
		Pageable pageable= PageRequest.of(pageNo-1, pageSize);
		return brandRepo.findAll(pageable);
	}

}
