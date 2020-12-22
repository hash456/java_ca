package sg.edu.iss.ca.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.service.UserService;
import sg.edu.iss.ca.validator.UserValidator;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userSvc;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.addValidators(new UserValidator());
//	}
	
	@GetMapping("/register")
	public String showRegisterForm(Model model, HttpSession session) {
		session.removeAttribute("registerSuccess");
		session.removeAttribute("usernameError");
		session.removeAttribute("emailError");
		model.addAttribute("staff", new Staff());
		return "RegisterForm";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("staff") @Valid Staff staff, 
			BindingResult bindingResult, HttpSession session, Model model) {
		if (bindingResult.hasErrors()) {	
			model.addAttribute("staff", staff);
			return "RegisterForm";
		}
		
		session.removeAttribute("registerSuccess");
		session.removeAttribute("usernameError");
		session.removeAttribute("emailError");
		
		staff.setRole("ROLE_ADMIN");
		staff.setEnabled(true);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		staff.setPassword(encoder.encode(staff.getPassword()));
		
		if(userSvc.findStaffByUsername(staff.getUserName()) != null) {
			session.setAttribute("usernameError", "true");
			model.addAttribute("staff", staff);
			return "RegisterForm";
		}
		
		if(userSvc.findStaffByEmail(staff.getEmail()) != null) {
			session.setAttribute("emailError", "true");
			model.addAttribute("staff", staff);
			return "RegisterForm";
		}
		
		Staff newStaff = userSvc.addStaff(staff);
		
		session.setAttribute("registerSuccess", "true");
		
		return "redirect:/login";
	}
}
