package com.demo.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.demo.springboot.model.Question;

@Repository
public class ProjectRepository implements CrudRepository<Question, Long>{
	
	@Autowired
    private QuestionRepository repository;
	
	public List<Question> retrieveAllQuestion() {
		
		List<Question> questions = (List<Question>) repository.findAll();
		
		return questions;
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Question arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Question> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Question> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Question> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Question> findById(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Question> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Question> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
