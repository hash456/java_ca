package sg.edu.iss.ca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.service.BrandImplement;
import sg.edu.iss.ca.service.BrandService;


@Controller
@RequestMapping("/brand")
public class BrandController {
	
	@Autowired
	private BrandService brandServ;
	
	@Autowired
	public void setBrandInterface(BrandImplement brandimpl) {
		this.brandServ = brandimpl;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		return findPaginated(1,model);
	}
	
	@GetMapping("/create")
	public String createBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "BrandForm";
	}

	@GetMapping("/delete/{id}")
	public String deleteBrand(@PathVariable("id") Integer id) {
		brandServ.deleteBrand(brandServ.findByBrandId(id));
		return "redirect:/brand/index";
	}
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editBrand(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("brand", brandServ.findByBrandId(id));
		return "BrandForm";
	}
	
	@PostMapping("/save")
	public String saveBrand(@ModelAttribute("brand") @Valid Brand brand, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "BrandForm";
		}
		brandServ.createBrand(brand);
		return "redirect:/brand/index";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value= "pageNo") int pageNo,Model model)
	{
		int pageSize=5;
		Page<Brand> page=brandServ.findPaginated(pageNo, pageSize);
		List<Brand> brandList=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("brands", brandList);
		return "BrandIndex";
	}


}
