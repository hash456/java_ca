package sg.edu.iss.ca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.service.FormCartImplement;
import sg.edu.iss.ca.service.FormCartService;
import sg.edu.iss.ca.service.InventoryImplement;
import sg.edu.iss.ca.service.InventoryService;
import sg.edu.iss.ca.service.ProductImplement;
import sg.edu.iss.ca.service.ProductService;

@Controller
@RequestMapping("/FormCart")
public class FormCartController {
	@Autowired
	private FormCartService fcservice;
	
	@Autowired
	public void setFormCartService(FormCartImplement formCartImple) {
		this.fcservice = formCartImple;
	}
	
	@Autowired
	private InventoryService iservice;
	
	@Autowired
	private void setInventoryService(InventoryImplement inventoryImple) {
		this.iservice = inventoryImple;
	}
	
	@RequestMapping(value = "/addToForm/{id}")
	public String addToForm(@PathVariable("id") Integer id, HttpSession session) {
		Inventory i = iservice.findInventoryById(id);
		UsageForm uf = (UsageForm) session.getAttribute("UsageForm");
		if (uf == null) {
			return "redirect:/UsageForm/add";
		}
		int fid = uf.getId();
		fcservice.addtoForm(i, fid);
		return "redirect:/UsageForm/details";
	}
}
