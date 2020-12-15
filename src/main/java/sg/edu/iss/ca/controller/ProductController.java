package sg.edu.iss.ca.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.repo.BrandRepository;
import sg.edu.iss.ca.service.ProductService;
import sg.edu.iss.ca.service.ProductImplement;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Autowired
	public void setProductservice(ProductImplement productimple) {
		this.pservice = productimple;
	}
    
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("ProductList", pservice.listAllProducts());
		return "ProductList";
	}
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("brandList", (ArrayList<Brand>)brandRepo.findAll());
		return "ProductForm";
	}
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", pservice.findProductById(id));
		return "ProductForm";
	}
	@RequestMapping(value = "/save")
	public String addProduct(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "ProductForm";
		}
		pservice.createProduct(product);
		return "redirect:/product/list";
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		pservice.deleteProduct(pservice.findProductById(id));
		return "redirect:/product/list";
	}

}
