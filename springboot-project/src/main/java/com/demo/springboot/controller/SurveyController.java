package com.demo.springboot.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.springboot.TimeLimitedCodeBlock;
import com.demo.springboot.model.Question;
import com.demo.springboot.model.Survey;
import com.demo.springboot.service.SurveyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
class SurveyController {
	@Autowired
	private SurveyService surveyService;
	
	private static List<Survey> list = null;
	
	@GetMapping("/surveys/questions")
	public List<Survey> retrieveAllSurveys() {
		
		
		
		try {
		      TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
		        @Override
		        public void run() {
		          try {
		        	
		        	  list= surveyService.retrieveAllSurveys();
		          }
		          catch (RuntimeException e) {
		            List<Survey> surveyArraylist = new ArrayList<Survey>();
				    	Survey surveyObj = new Survey("1","Exception ::","Runtime Exception", null);
				    	surveyArraylist.add(surveyObj);
				    	list = surveyArraylist;
				    	//return surveyArraylist;
		          }
		        }
		      }, 7, TimeUnit.SECONDS);
		    }catch(RuntimeException runtimeException){
		    	
		    	List<Survey> surveyArraylist = new ArrayList<Survey>();
		    	Survey surveyObj = new Survey("1","Service Exception","Runtime Exception", null);
		    	surveyArraylist.add(surveyObj);
		    	return surveyArraylist;
		    	
		    	
		    }
		    catch (TimeoutException e) {
		       //
		    	List<Survey> surveyArraylist = new ArrayList<Survey>();
		    	Survey surveyObj = new Survey("1","Response Timed out from Service","Timout out please try again", null);
		    	surveyArraylist.add(surveyObj);
		    	return surveyArraylist;
		    } catch (Exception ex) {
		    	
		    	List<Survey> surveyArraylist = new ArrayList<Survey>();
		    	Survey surveyObj = new Survey("1","Service Exception","Timout out please try again", null);
		    	surveyArraylist.add(surveyObj);
		    	return surveyArraylist;
		    	
		    }
		    //log(startTime, "end of main method!");

		
		
		
		
		
		
		
		
		
		/*try{
		      return surveyService.retrieveAllSurveys();
		}catch(Exception ex){
			System.out.println("Exception Chaos Monkey::" +ex);
		}*/
		
		return list;
	}

	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestions(@PathVariable String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}

	// GET "/surveys/{surveyId}/questions/{questionId}"
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId,
			@PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}

	// /surveys/{surveyId}/questions
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(
			@PathVariable String surveyId, @RequestBody Question newQuestion) {

		Question question = surveyService.addQuestion(surveyId, newQuestion);

		if (question == null)
			return ResponseEntity.noContent().build();

		// Success - URI of the new resource in Response Header
		// Status - created
		// URI -> /surveys/{surveyId}/questions/{questionId}
		// question.getQuestionId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(question.getId()).toUri();

		// Status
		return ResponseEntity.created(location).build();
	}

}