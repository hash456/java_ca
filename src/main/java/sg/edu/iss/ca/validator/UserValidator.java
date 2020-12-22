package sg.edu.iss.ca.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors; 
import org.springframework.validation.Validator;

import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.service.UserService; 

public class UserValidator implements Validator {
	
		@Autowired
		public UserService userSvc;
		
        public boolean supports(Class clazz) {
             return Staff.class.equals(clazz);
        }
        
        public void validate(Object obj, Errors e) { 
        	Staff staff = (Staff) obj;
        	if(userSvc.findStaffByUsername(staff.getUserName()) != null) {
        		e.rejectValue("userName", "userName");
        	}
        	if(userSvc.findStaffByEmail(staff.getEmail()) != null) {
        		e.rejectValue("email", "email");
        	}
        } 
}
