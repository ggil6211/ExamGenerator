package edu.deakin.sit218.examgeneration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;

import edu.deakin.sit218.examgeneration.config.ExamGenSpringSecurityConfig;
import edu.deakin.sit218.examgeneration.entity.questionandanswer;

//loads the model with the questionandanswer entity and returns the examgen view page
@Controller
public class ExamGenController {
	protected static Logger logger =LogManager.getLogger(ExamGenController.class);
	
	@GetMapping("/examgen")
	public String showForm(Model model) {
		questionandanswer qanda = new questionandanswer();
		model.addAttribute("qanda", qanda);
		
		//logs user using resource
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("---THE_USER_LOGIN_IS @ EXAMGEN: " +authentication.getName() + "--"+authentication.getAuthorities()
		+"--Session ID - "+ RequestContextHolder.currentRequestAttributes().getSessionId());

		return "examgen";
	}
	
	
}

