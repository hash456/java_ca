package sg.edu.iss.ca.controller;



import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.email.AccountMail;
import sg.edu.iss.ca.email.SimpleMail;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.service.MailSenderService;
import sg.edu.iss.ca.service.UserImplement;
import sg.edu.iss.ca.service.UserService;

@Controller
@RequestMapping("/staff")
public class UserController {
	
		@Autowired
		private UserService uservice;
		
		@Autowired
	    private MailSenderService senderService;
		
		@Autowired
		public void setUserservice(UserImplement userimple) {
			this.uservice = userimple;
		}
	    
		@RequestMapping(value = "/list")
		public String list(Model model) {
			return findPaginated(1,model);
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
			
			if(staff.getPassword().isEmpty()) {
				staff.setEnabled(true);
				String password = this.GenerateRandomPassword();
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
				staff.setPassword(encoder.encode(password));
				senderService.sendAccountMail(new AccountMail(staff.getEmail()), staff.getUserName(), password);
			}
			
			uservice.addStaff(staff);
			return "redirect:/staff/list";
		}
		
		@RequestMapping(value = "/delete/{staffId}")
		public String deleteStaff(@PathVariable("staffId") Integer staffId) {
			uservice.deleteStaff(uservice.findStaffById(staffId));
			return "redirect:/staff/list";
		}
		@RequestMapping(value = "/change/{staffId}")
		public String ChangeRole(@PathVariable("staffId") Integer staffId, HttpServletRequest httpServletRequest) {
			Staff s = uservice.changeRole(uservice.findStaffById(staffId));
//			if(s.getRole().equals("ROLE_MECHANIC") && s.getUserName().equals(httpServletRequest.getRemoteUser()))
//				return "redirect:/logout";
			return "redirect:/staff/list";
		}

		private String GenerateRandomPassword() {
			   // create a string of all characters
		    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		    // create random string builder
		    StringBuilder sb = new StringBuilder();

		    // create an object of Random class
		    Random random = new Random();

		    // specify length of random string
		    int length = 7;

		    for(int i = 0; i < length; i++) {

		      // generate random index number
		      int index = random.nextInt(alphabet.length());

		      // get character specified by index
		      // from the string
		      char randomChar = alphabet.charAt(index);

		      // append the character to string builder
		      sb.append(randomChar);
		    }

		    String randomString = sb.toString();
		    System.out.println("Random String is: " + randomString);
		    return randomString;
		}
		
		@GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable (value= "pageNo") int pageNo,Model model)
		{
			int pageSize=5;
			Page<Staff> page=uservice.findPaginated(pageNo, pageSize);
			List<Staff> liststaffs=page.getContent();
			model.addAttribute("currentPage",pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems",page.getTotalElements());
			model.addAttribute("StaffList", liststaffs);
			return "StaffList";
		}

}
