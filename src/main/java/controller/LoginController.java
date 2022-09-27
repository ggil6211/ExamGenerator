package edu.deakin.sit218.examgeneration.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
//directs the app to the main login or access deined view page
@Controller
public class LoginController {
	protected static Logger logger =LogManager.getLogger(AdminController.class);

	//Logs if the user has logged out
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "fancy-login";
		
	}
	
	// Add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		//logs user being denied access to resource
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.error("---THE_USER_LOGIN_HAS_BEEN_DENIED_ACCESS: " +authentication.getName() 
		+ "--"+authentication.getAuthorities()+"--Session ID - "
				+ RequestContextHolder.currentRequestAttributes().getSessionId());
		return "access-denied";
	}
	
}
