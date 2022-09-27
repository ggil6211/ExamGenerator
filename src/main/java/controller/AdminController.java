package edu.deakin.sit218.examgeneration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;

import edu.deakin.sit218.examgeneration.config.ExamGenSpringSecurityConfig;

@Controller
public class AdminController {
	protected static Logger logger =LogManager.getLogger(AdminController.class);
	
	@GetMapping("/admin")
	public String showHome() {
		//logs user using resource
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("---THE_USER_LOGIN_IS @ ADMIN: " +authentication.getName() + "--"+authentication.getAuthorities()
		+"--Session ID - "+ RequestContextHolder.currentRequestAttributes().getSessionId());

		return "admin";
	}
}
