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
			QandADAO dao = new QandADAOImpl();
			
			if(!dao.existsKnowledge(qanda)) {
				model.addAttribute("message","<h1 style=\"color:Tomato;text-align:center;\">Knowledge : "
						+ qanda.getKnowledge() +"</h1><h3 style=\"color:Tomato;text-align:center;\"> has no Question and Answers"
						+" in the database<br>Please try again</h3><hr style=\"width:50%\">");
				model.addAttribute("list",null);	
			}
			else {
				List<questionandanswer> qandalist;
				qandalist = dao.queryQandA(qanda);
				model.addAttribute("list",qandalist);
			}
		return "query-dbresult";
	}
	
	
}
