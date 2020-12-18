package sg.edu.iss.ca.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import sg.edu.iss.ca.model.AdminLog;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.service.AdminLogService;
import sg.edu.iss.ca.service.InventoryService;
import sg.edu.iss.ca.service.UserService;

@Controller
@RequestMapping("/manage")
public class ManageInventoryController {
	@Autowired
	private InventoryService inventorySvc;
	@Autowired
	private AdminLogService adminSvc;
	@Autowired
	private UserService uSvc;
	
	@GetMapping(value = "/restock/{id}")
	public String restockForm(@PathVariable("id") Integer id, Model model) {
		Inventory inventory = inventorySvc.findByInventoryId(id);
		model.addAttribute("inventory", inventory);
		return "RestockForm";
	}
	
	@PostMapping(value = "/restock/save") 
	public String restockInventory(@ModelAttribute("inventory") @Valid Inventory inventory, 
			BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			return "RestockForm";
		}
	
		inventorySvc.restockInventory(inventory, httpServletRequest);
		
		return "redirect:/inventory/list";
	}
	
	@GetMapping(value = "/withdraw/{id}")
	public String withdrawForm(@PathVariable("id") Integer id, Model model) {
		Inventory inventory = inventorySvc.findByInventoryId(id);
		model.addAttribute("inventory", inventory);
		return "WithdrawForm";
	}
	
	@PostMapping(value = "/withdraw/save") 
	public String withdrawInventory(@ModelAttribute("inventory") @Valid Inventory inventory, 
			BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			return "WithdrawForm";
		}
		
		inventorySvc.withdrawInventory(inventory, httpServletRequest);
		
		return "redirect:/inventory/list";
	}
}
