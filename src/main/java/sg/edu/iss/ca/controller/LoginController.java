package sg.edu.iss.ca.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController 
{
    @GetMapping("/login")
    public String viewLoginPage() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    		return "login";
    	}
        return "index";
    }
    
    @GetMapping("/logout")
    public String viewLogoutPage() {
    	return "CustomLogout";
    }
}
