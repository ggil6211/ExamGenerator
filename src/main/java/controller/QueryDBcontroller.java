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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


//business logic for querying the database with q and a from knowledge base

@Controller 
@RequestMapping("/question-answer")
public class QueryDBcontroller {
	
	@RequestMapping("/process-query")
	public String querydatabase(@Valid @ModelAttribute("query")questionandanswer qanda, 
			BindingResult validationErrors, Model model) {
		
			//creates a instance of the object to use it methods to access the database
			QandADAO dao = new QandADAOImpl();
			
			//checks if the q and a doesn't exist 
			if(!dao.existsKnowledge(qanda)) {
				model.addAttribute("message","<h1 style=\"color:Tomato;text-align:center;\">Knowledge : "+ qanda.getKnowledge() +"</h1><h3 style=\"color:Tomato;text-align:center;\"> has no Question and Answers in the database<br>Please try again</h3><hr style=\"width:50%\">");
			}
			else {
				//if does exist load the list with all the q and a from the knowledge base
				List<questionandanswer> qandalist;
				qandalist = dao.queryQandA(qanda);
				
				//adds all question to a string
				String message="";
				for (questionandanswer q:qandalist) {
					message = message + ("<p style=\"color:Purple\">Question :</p> "+ q.getQuestion() + "<p style=\"color:Purple\">Answer: </p>" +q.getAnswer() + "<p style=\"color:Purple\"> Knowledge </p>" +q.getKnowledge()+"<hr style=\"width:50%\">");
				}
				//returns the model with all the questions
				model.addAttribute("message",message);
			}
		return "query-dbresult";
	}
	
	
}
