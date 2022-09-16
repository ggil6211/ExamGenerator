package edu.deakin.sit218.examgeneration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import edu.deakin.sit218.examgeneration.config.ExamGenSpringSecurityConfig;
//directs the app to the main login or access deined view page
@Controller
public class LoginController {
	protected static Logger logger =LogManager.getLogger(ExamGenSpringSecurityConfig.class);

	//Logs if the user has logged out
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getName()=="anonymousUser") {
			String currentUsername = authentication.getName();
			logger.info("---------------------USER_LOGOUT");	
		}
		return "fancy-login";
		
	}
	
	// Add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
}
