package sg.edu.iss.ca.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Role;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.service.UserImplement;
import sg.edu.iss.ca.service.UserService;

@Controller
@RequestMapping("/staff")
public class UserController {
	
		@Autowired
		 UserService uservice;
		
	
		
		@Autowired
		public void setUserservice(UserImplement userimple) {
			this.uservice = userimple;
		}
	    
		@RequestMapping(value = "/list")
		public String list(Model model) {
			model.addAttribute("StaffList", uservice.liststaff());
			return "StaffList";
		}
		@RequestMapping(value = "/add")
		public String addForm(Model model) {
			model.addAttribute("staff", new Staff());
		
			return "StaffForm";
		}
		@RequestMapping(value = "/edit/{staffId}")
		public String editForm(@PathVariable("staffId") Integer staffId, Model model) {
			model.addAttribute("staff", uservice.findStaffById(staffId));
			return "StaffForm";
		}
		
		@RequestMapping(value = "/save")
		public String addStaff(@ModelAttribute("staff") @Valid Staff staff, 
				BindingResult bindingResult,  Model model) {
			if (bindingResult.hasErrors()) {
				return "StaffForm";
			}
			staff.setRole(Role.ADMIN);
			uservice.addStaff(staff);
			return "redirect:/staff/list";
		}
		@RequestMapping(value = "/delete/{staffId}")
		public String deleteStaff(@PathVariable("staffId") Integer staffId) {
			uservice.deleteStaff(uservice.findStaffById(staffId));
			return "redirect:/staff/list";
		}
		@RequestMapping(value = "/change/{staffId}")
		public String ChangeRole(@PathVariable("staffId") Integer staffId) {
			uservice.changeRole(uservice.findStaffById(staffId));
			return "redirect:/staff/list";
		}


}
