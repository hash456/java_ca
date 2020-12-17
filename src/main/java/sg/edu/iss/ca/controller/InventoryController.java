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
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Supplier;
import sg.edu.iss.ca.service.ProductService;
import sg.edu.iss.ca.service.SupplierService;
import sg.edu.iss.ca.service.BrandService;
import sg.edu.iss.ca.service.InventoryImplement;
import sg.edu.iss.ca.service.InventoryService;
import sg.edu.iss.ca.service.ProductImplement;
import sg.edu.iss.ca.service.SupplierImplement;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inservice;
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private SupplierService sservice;
	
	@Autowired
	public void setInventoryservice(InventoryImplement inventimple) {
		this.inservice = inventimple;
	}
    
	

	
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("InventoryList", inservice.listAllInventories());
		return "InventoryList";
	}
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("inventory", new Inventory());
		model.addAttribute("productList", (ArrayList<Product>)pservice.listAllProducts());
		model.addAttribute("supplierList", (ArrayList<Supplier>)sservice.listAllSuppliers());
		return "InventoryForm";
	}
	
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Inventory i = inservice.findByInventoryId(id);
		
		if(i.getProduct() != null)
			i.setProductName(i.getProduct().getName());
		
		if(i.getSupplier() != null)
			i.setSupplierName(i.getSupplier().getName());
		
		model.addAttribute("inventory", i);
		model.addAttribute("productList", (ArrayList<Product>)pservice.listAllProducts());
		model.addAttribute("supplierList", (ArrayList<Supplier>)sservice.listAllSuppliers());
		return "InventoryForm";
	}
		
	@RequestMapping(value = "/save")
	public String addInventory(@ModelAttribute("inventory") @Valid Inventory inventory, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "InventoryForm";
		}
				
		// Find if the name of the brand is in the database
		Product p = pservice.findByProductName(inventory.getProductName());
		if(p != null)
			inventory.setProduct(p);
		
		else if(!inventory.getProductName().trim().isEmpty()) {
			Product newProduct = new Product();
			newProduct.setName(inventory.getProductName().trim());
			pservice.createProduct(newProduct);
			inventory.setProduct(newProduct);
		}
		
		Supplier s = sservice.findBySupplierName(inventory.getSupplierName());
		if(s != null)
			inventory.setSupplier(s);
		
		else if(!inventory.getSupplierName().trim().isEmpty()) {
			Supplier newSupplier = new Supplier();
			newSupplier.setName(inventory.getSupplierName().trim());
			sservice.createSupplier(newSupplier);
			inventory.setSupplier(newSupplier);
		}
		
		
		inservice.createInventory(inventory);
		
		return "redirect:/inventory/list";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteInventory(@PathVariable("id") Integer id) {
		inservice.deleteInventory(inservice.findByInventoryId(id));
		return "redirect:/inventory/list";
	}

}
