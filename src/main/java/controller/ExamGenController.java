package edu.deakin.sit218.examgeneration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.deakin.sit218.examgeneration.entity.questionandanswer;

//loads the model with the questionandanswer entity and returns the examgen view page
@Controller
public class ExamGenController {
	@GetMapping("/examgen")
	public String showForm(Model model) {
		questionandanswer qanda = new questionandanswer();
		model.addAttribute("qanda", qanda);
		return "examgen";
	}
}

