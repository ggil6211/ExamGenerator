package edu.deakin.sit218.examgeneration.dao;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;

import edu.deakin.sit218.examgeneration.entity.questionandanswer;

public class QandADAOImpl implements QandADAO {

	protected Logger logger =LogManager.getLogger(QandADAOImpl.class);
	protected String currentUsername; 
	protected String sessionID;
	protected Authentication authentication;
	
	public String getCurrentUsername() {
		return currentUsername;
	}

	public void setCurrentUsername(String currentUsername) {
		this.currentUsername = currentUsername;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	SessionFactory factory;
	public QandADAOImpl() {
		 	factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(questionandanswer.class)
				.buildSessionFactory();
		 	authentication= SecurityContextHolder.getContext().getAuthentication();
			setCurrentUsername(authentication.getName());
			setSessionID(RequestContextHolder.currentRequestAttributes().getSessionId());
	}	
	
	@Override
	public void updateQandA(questionandanswer qanda) {
		Session session = factory.getCurrentSession();
		
		try{
			session.beginTransaction();
			session.update(qanda);
			session.getTransaction().commit();
			logger.info("---DATABASE_UPDATE(UPDATE QANDA)--- BY USER : "+ getCurrentUsername()
					+ " ROLE - "+authentication.getAuthorities() +
					"--QUESTION: " + qanda.getQuestion() +"--ANSWER: " + qanda.getAnswer() +
					" --KNOWLEDGE" + qanda.getKnowledge()+"--SessoinID:" + getSessionID());
		}
		finally {
			session.close();
		}
	}

	//inserts a q and a to the database
	@Override
	public void insertQandA(questionandanswer qanda) {
		Session session = factory.getCurrentSession();
		try{
					
			session.beginTransaction();
			session.save(qanda);
			session.getTransaction().commit();
			logger.info("---DATABASE_INSERT(INSERT QANDA)--- BY USER : "+ getCurrentUsername() + " ROLE - "
					+authentication.getAuthorities() + 	"--QUESTION: " + qanda.getQuestion() +"--ANSWER: " 
					+ qanda.getAnswer() +" --KNOWLEDGE" + qanda.getKnowledge()+"--SessoinID:" + getSessionID());
		}
		finally {
			session.close();
		}
	}
	
	//retrieves the q and a from the database
	@Override
	public questionandanswer retrieveQandA(questionandanswer qanda) {
	Session session = factory.getCurrentSession();
		
		try{
					
			session.beginTransaction();
			String hql = "from questionandanswer where question =:question and answer =:answer";
			Query<questionandanswer> query = session.createQuery(hql);
			query.setParameter("question", qanda.getQuestion());
			query.setParameter("answer", qanda.getAnswer());
			List<questionandanswer> questions =query.getResultList();

			logger.info("---RETRIEVE_FROM_DATABASE(QAND RETRIEVE)--- BY USER : "+ getCurrentUsername()+ " ROLE - "
					+authentication.getAuthorities() +"--QUESTION: " + qanda.getQuestion() +"--ANSWER: " 
					+ qanda.getAnswer() +"--SessoinID:" + getSessionID());
			return questions.get(0);
		}
		finally {
			session.close();
		}
	}

	//checks if the question exists in the database
	@Override
	public boolean existsQandA(questionandanswer qanda) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			String hql = "from questionandanswer where question =:question and answer =:answer";
			Query<questionandanswer> query = session.createQuery(hql);
			query.setParameter("question", qanda.getQuestion());
			query.setParameter("answer", qanda.getAnswer());
			List<questionandanswer> questions =query.getResultList();

			logger.info("---Query_Database(QAND EXIST QUERY)--- BY USER : "+ getCurrentUsername() 
					+ " ROLE - "+authentication.getAuthorities() +
					"--QUESTION: " + qanda.getQuestion() +"--ANSWER: " + 
					qanda.getAnswer() +"--SessoinID:" + getSessionID());
			return !questions.isEmpty();
			
		}
		finally {
			session.close();
		}
	}
	
	//checks if the knowledge base exists
	@Override
	public boolean existsKnowledge(questionandanswer qanda) {
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "from questionandanswer where knowledge =:knowledge ";
			Query<questionandanswer> query = session.createQuery(hql);
			query.setParameter("knowledge", qanda.getKnowledge());
			List<questionandanswer> questions =query.getResultList();
			logger.info("---Query_Database(KNOWLEDGE EXIST QUERY)--- BY USER : "+ getCurrentUsername()+ 
					" ROLE - " +authentication.getAuthorities() +"--KNOWLEDGE: " + qanda.getKnowledge() 
					+" --SessoinID:" + getSessionID());
			return !questions.isEmpty();	
		}
		finally {
			session.close();
		}
	}
	
	//queries the database to get all questions for a knowledge base
	@Override
	public  List<questionandanswer> queryQandA(questionandanswer qanda) {
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "from questionandanswer where knowledge =:knowledge ";
			Query<questionandanswer> query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(5);
			query.setParameter("knowledge", qanda.getKnowledge());
			List<questionandanswer> questions =query.getResultList();
			logger.info("---Query_Database(BY KNOWLEDGE)--- BY USER : "+ getCurrentUsername() + " ROLE - "
					+authentication.getAuthorities() + "--KNOWLEDGE: " + qanda.getKnowledge() 
					+"--SessoinID:" + getSessionID());
			
			return questions;
		}
		finally {
			session.close();
		}
	}

	@Override
	protected void finalize() throws Throwable{
		factory.close();
		
	}
}
