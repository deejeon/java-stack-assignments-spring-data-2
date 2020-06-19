package com.danieljeon.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.dojooverflow.models.Answer;
import com.danieljeon.dojooverflow.models.Question;
import com.danieljeon.dojooverflow.repositories.AnswerRepo;
import com.danieljeon.dojooverflow.repositories.QuestionRepo;
import com.danieljeon.dojooverflow.repositories.TagRepo;

@Service
public class AnswerService {
	@Autowired
	private final QuestionRepo questionRepo;
	
	@Autowired
	private final AnswerRepo answerRepo;
	
	@Autowired
	private final TagRepo tagRepo;
	
	public AnswerService(QuestionRepo qr, AnswerRepo ar, TagRepo tr) {
		this.questionRepo = qr;
		this.answerRepo = ar;
		this.tagRepo = tr;
	}
	
	public List<Answer> all() {
		return answerRepo.findAll();
	}
	
	public Answer create(Answer answer) {
		return answerRepo.save(answer);
	}
	
	public Answer find(Long id) {
		Optional<Answer> optionalAnswer = answerRepo.findById(id);
		if (optionalAnswer.isPresent()) return optionalAnswer.get();
		else return null;
	}
	
	public Answer addAnswerToQuestion(Long answerId, Long questionId) {
		Optional<Question> optionalQuestion = questionRepo.findById(questionId);
		Question question = optionalQuestion.get();
		Optional<Answer> optionalAnswer = answerRepo.findById(answerId);
		Answer answer = optionalAnswer.get();
		answer.setQuestion(question);
		return answerRepo.save(answer);
	}
}
