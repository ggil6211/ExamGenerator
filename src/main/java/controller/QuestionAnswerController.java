package edu.deakin.sit218.examgeneration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import edu.deakin.sit218.examgeneration.config.ExamGenSpringSecurityConfig;
import edu.deakin.sit218.examgeneration.entity.questionandanswer;


@Controller
public class QuestionAnswerController {
	
	protected static Logger logger =LogManager.getLogger(QuestionAnswerController.class);
	
	@GetMapping("/question-answer")
	
	public String showForm(Model model) {
		
		//logs user using resource
		questionandanswer qanda = new questionandanswer();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("---THE_USER_LOGIN_IS @ QuestionAnswer: " +authentication.getName() + "--"+authentication.getAuthorities()
		+"--Session ID - "+ RequestContextHolder.currentRequestAttributes().getSessionId());

		model.addAttribute("qanda", qanda);
		return "question-answer";
	}
}