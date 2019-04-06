package com.demo.springboot.jpa;

import org.springframework.data.repository.CrudRepository;

import com.demo.springboot.model.Question;

public interface QuestionRepository extends CrudRepository<Question, String> {

}
