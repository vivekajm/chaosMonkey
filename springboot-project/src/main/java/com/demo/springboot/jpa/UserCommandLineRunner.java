package com.demo.springboot.jpa;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.springboot.model.Question;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory
            .getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;
    
    @Autowired 
    QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        // save a couple of customers
        repository.save(new User("Vivek", "Admin"));
        repository.save(new User("Ravi", "User"));
        repository.save(new User("Satish", "Admin"));
        repository.save(new User("Raghu", "User"));

        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }
        
        
        
        Question question1 = new Question(new Long("1"),
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
        questionRepository.save(question1);
        
		Question question2 = new Question(new Long("2"),
				"Most Populus Country in the World", "China", Arrays.asList(
						"India", "Russia", "United States", "China"));
		questionRepository.save(question2);
		
		Question question3 = new Question(new Long("3"),
				"Highest GDP in the World", "United States", Arrays.asList(
						"India", "Russia", "United States", "China"));
		questionRepository.save(question3);
		
		Question question4 = new Question(new Long("4"),
				"Second largest english speaking country", "India", Arrays
						.asList("India", "Russia", "United States", "China"));
        
		questionRepository.save(question4);

    }

}