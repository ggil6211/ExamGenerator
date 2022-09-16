package edu.deakin.sit218.examgeneration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import edu.deakin.sit218.examgeneration.config.ExamGenSpringSecurityConfig;
//directs the user to the home page and logs the user thats logged in
@Controller
public class HomeController {
	
	protected static Logger logger =LogManager.getLogger(ExamGenSpringSecurityConfig.class);

	@GetMapping("/")
	public String showHome() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		logger.info("----------------------THE_USER_LOGIN : " +currentUsername);
		return "home";
	}
}
