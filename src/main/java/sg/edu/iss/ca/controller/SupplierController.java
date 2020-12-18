package sg.edu.iss.ca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.model.Supplier;
import sg.edu.iss.ca.service.SupplierImplement;
import sg.edu.iss.ca.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierSvc;
	
	@Autowired
	public void setBrandInterface(SupplierImplement supplierImpl) {
		this.supplierSvc = supplierImpl;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		return findPaginated(1,model);
	}
	
	@GetMapping("/create")
	public String createSupplier(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "SupplierForm";
	}

	@GetMapping("/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Integer id) {
		supplierSvc.deleteSupplier(supplierSvc.findSupplierById(id));
		return "redirect:/supplier/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editSupplier(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("supplier", supplierSvc.findSupplierById(id));
		return "SupplierForm";
	}
	
	@PostMapping("/save")
	public String saveBrand(@ModelAttribute("brand") @Valid Supplier supplier, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "SupplierForm";
		}
		supplierSvc.createSupplier(supplier);
		return "redirect:/supplier/index";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value= "pageNo") int pageNo,Model model)
	{
		int pageSize=5;
		Page<Supplier> page=supplierSvc.findPaginated(pageNo, pageSize);
		List<Supplier> supplierList=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("suppliers", supplierList);
		return "SupplierIndex";
	}
}
