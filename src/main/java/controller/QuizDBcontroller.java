package edu.deakin.sit218.examgeneration.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.deakin.sit218.examgeneration.dao.QandADAO;
import edu.deakin.sit218.examgeneration.dao.QandADAOImpl;
import edu.deakin.sit218.examgeneration.entity.questionandanswer;

import javax.validation.Valid;

//business logic for the adding of q and a to the database
@Controller 
@RequestMapping("/question-answer")
public class QuizDBcontroller {

	@RequestMapping("/process-qanda")
	public String addtodatabase(@Valid @ModelAttribute("qanda")questionandanswer qanda, 
			BindingResult validationErrors, Model model) {
		if(validationErrors.hasErrors()) 
			return "question-answer";
	
			//creates a instance of the object to use it methods to access the database
			QandADAO dao = new QandADAOImpl();
	
			//if the question doesnt exist add to the database
			if(!dao.existsQandA(qanda))
				dao.insertQandA(qanda);
			else {
				//if the question does exist tell the user
				model.addAttribute("message","<hr style=\"width:30%\"><br>Question : " + qanda.getQuestion()+"<br>Answer : "
				+ qanda.getAnswer()+ "<br>Knowledge : "+qanda.getKnowledge()+""
						+"<br><h1 style=\"color:Tomato;text-align:center;\">This Question and Answer already Exists</h1><h2 h1" 
						+"style=\"text-align:center;\" style=\"text-align:center;\"><br>Please try again</h1><hr style=\"width:30%\">");
				return "qanda-dbresult";
			}
			//tell the user the question has been added to database
			qanda = dao.retrieveQandA(qanda);
			model.addAttribute("message","Question : " + qanda.getQuestion()+"<br>Answer : "+ qanda.getAnswer()+ "<br>Knowledge : "+qanda.getKnowledge()+""
			+"<br><h1 style=\"color:green;text-align:center;\">The question has been successfully<br>added to the database");
		return "qanda-dbresult";
	}
}

