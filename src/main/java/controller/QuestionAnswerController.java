package edu.deakin.sit218.examgeneration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.deakin.sit218.examgeneration.entity.questionandanswer;

@Controller
public class QuestionAnswerController {
	@GetMapping("/question-answer")

	public String showForm(Model model) {
		questionandanswer qanda = new questionandanswer();

		model.addAttribute("qanda", qanda);
		return "question-answer";
	}
}