package com.demo.springboot.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.springboot.TimeLimitedCodeBlock;
import com.demo.springboot.jpa.ProjectRepository;
import com.demo.springboot.model.Question;
import com.demo.springboot.model.Survey;


@Service
public class SurveyService {

	
	//@Autowired
    //private QuestionRepository repository;
	
	@Autowired
	ProjectRepository repository;
	
	private static List<Survey> surveys = new ArrayList<>();
	/*static {
		
		Question question1 = new Question(new Long("1"),
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question2 = new Question(new Long("2"),
				"Most Populus Country in the World", "China", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question3 = new Question(new Long("3"),
				"Highest GDP in the World", "United States", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question4 = new Question(new Long("4"),
				"Second largest english speaking country", "India", Arrays
						.asList("India", "Russia", "United States", "China"));

		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
				question2, question3, question4));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
				"Description of the Survey", questions);

		surveys.add(survey);
	}*/
	public List<Survey> retrieveAllSurveys() {
		
		
		try {
		      TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
		        @Override
		        public void run() {
		          try {
		        	  
		        	  
		        	  List<Question> questions = (List<Question>) repository.retrieveAllQuestion();
		        	  
		      		Survey survey = new Survey("Survey1", "My Favorite Survey",
		      				"Description of the Survey", questions);
		      		
		      		surveys.clear();
		      		
		      		surveys.add(survey);       	  
		        	  
		          }
		          catch (Exception e) {
//		            log(startTime, "was interrupted!");
		          }
		        }
		      }, 5, TimeUnit.SECONDS);
		    }
		    catch (TimeoutException e) {
		       //
		    	List<Survey> surveyArraylist = new ArrayList<Survey>();
		    	Survey surveyObj = new Survey("1","Response Timed out from Repository","Timout out please try again", null);
		    	surveyArraylist.add(surveyObj);
		    	return surveyArraylist;
		    } catch (Exception ex) {}
		    //log(startTime, "end of main method!");
		
		
		
		/*List<Question> questions = (List<Question>) repository.retrieveAllQuestion();
		Survey survey = new Survey("Survey1", "My Favorite Survey",
				"Description of the Survey", questions);
		
		surveys.clear();
		
		surveys.add(survey);*/
		
		return surveys;
	}

	public Survey retrieveSurvey(String surveyId) {
		for (Survey survey : surveys) {
			if (survey.getId().equals(surveyId)) {
				return survey;
			}
		}
		return null;
	}

	public List<Question> retrieveQuestions(String surveyId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		return survey.getQuestions();
	}

	public Question retrieveQuestion(String surveyId, String questionId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		for (Question question : survey.getQuestions()) {
			if (question.getId().equals(questionId)) {
				return question;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		question.setId(new Long(randomId));

		survey.getQuestions().add(question);

		return question;
	}
}