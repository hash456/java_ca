package sg.edu.iss.ca.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return findPaginated(1, model);

	}

	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("inventory", new Inventory());
		model.addAttribute("productList", (ArrayList<Product>) pservice.listAllProducts());
		model.addAttribute("supplierList", (ArrayList<Supplier>) sservice.listAllSuppliers());
		return "InventoryForm";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Inventory i = inservice.findByInventoryId(id);

		if (i.getProduct() != null)
			i.setProductName(i.getProduct().getName());

		if (i.getSupplier() != null)
			i.setSupplierName(i.getSupplier().getName());

		model.addAttribute("inventory", i);
		model.addAttribute("productList", (ArrayList<Product>) pservice.listAllProducts());
		model.addAttribute("supplierList", (ArrayList<Supplier>) sservice.listAllSuppliers());
		return "InventoryForm";
	}

	@RequestMapping(value = "/save")
	public String addInventory(@ModelAttribute("inventory") @Valid Inventory inventory, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "InventoryForm";
		}

		if (inventory.getProductName() == null || inventory.getProductName().trim().length() == 0) {
			return "InventoryForm";
		}

		if (inventory.getSupplierName() == null || inventory.getSupplierName().trim().length() == 0) {
			return "InventoryForm";
		}

		// Find if the name of the brand is in the database
		Product p = pservice.findByProductName(inventory.getProductName());
		if (p != null)
			inventory.setProduct(p);

		else if (!inventory.getProductName().trim().isEmpty()) {
			Product newProduct = new Product();
			newProduct.setName(inventory.getProductName().trim());
			pservice.createProduct(newProduct);
			inventory.setProduct(newProduct);
		}

		Supplier s = sservice.findBySupplierName(inventory.getSupplierName());
		if (s != null)
			inventory.setSupplier(s);

		else if (!inventory.getSupplierName().trim().isEmpty()) {
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

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Inventory> page = inservice.findPaginated(pageNo, pageSize);
		List<Inventory> listInventories = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("InventoryList", listInventories);
		return "InventoryList";
	}

	@RequestMapping(value = "/generate/{id}")
	@ResponseBody
	public FileSystemResource generateReport(@PathVariable("id") int id, HttpServletResponse response) {
		File file = inservice.ReorderReportGenerate(id);
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			try {
				int c;
				while ((c = inputStream.read()) != -1) {
					response.getWriter().write(c);
				}
			} finally {
				if (inputStream != null)
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				response.getWriter().close();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		return new FileSystemResource(file.toPath());
//		return "redirect:/supplier/index";
	}

	@GetMapping("/listInventories")
	public String listInventoryForm(Model model, @Param("keyword") String keyword) {
		List<Inventory> listInventories = inservice.listAllInventories(keyword);
		model.addAttribute("InventoryList", listInventories);
		model.addAttribute("keyword", keyword);
		return "InventoryList";
	}

}
