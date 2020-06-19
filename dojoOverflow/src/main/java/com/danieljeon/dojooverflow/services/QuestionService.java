package com.danieljeon.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.dojooverflow.models.Answer;
import com.danieljeon.dojooverflow.models.Question;
import com.danieljeon.dojooverflow.models.Tag;
import com.danieljeon.dojooverflow.repositories.AnswerRepo;
import com.danieljeon.dojooverflow.repositories.QuestionRepo;
import com.danieljeon.dojooverflow.repositories.TagRepo;

@Service
public class QuestionService {
	@Autowired
	private final QuestionRepo questionRepo;
	
	@Autowired
	private final AnswerRepo answerRepo;
	
	@Autowired
	private final TagRepo tagRepo;
	
	public QuestionService(QuestionRepo qr, AnswerRepo ar, TagRepo tr) {
		this.questionRepo = qr;
		this.answerRepo = ar;
		this.tagRepo = tr;
	}
	
	public List<Question> all() {
		return questionRepo.findAll();
	}
	
	public Question create(Question question) {
		return questionRepo.save(question);
	}
	
	public Question create(String question) {
		return questionRepo.save(new Question(question));
	}
	
	public Question find(Long id) {
		Optional<Question> optionalQuestion = questionRepo.findById(id);
		if (optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		}
		else return null;
	}
	
	public String listTags(Long id) {
		String list = "";
		Question question = this.find(id);
		List<Tag> tags = question.getTags();
		for (int i = 0; i < tags.size(); i++) {
			list += tags.get(i);
			if (i < tags.size() - 1) {
				list += ", ";
			}
		}
		return list;
	}
	
	public Question setTagList(Long questionId, List<Long> tags) {
		Optional<Question> optionalQuestion = questionRepo.findById(questionId);
		Question question = optionalQuestion.get();
		List<Tag> tagList = new ArrayList<>();
		for (Long tagId : tags) {
			Optional<Tag> optionalTag = tagRepo.findById(tagId);
			Tag tag = optionalTag.get();
			System.out.println(tag.getId());
			tagList.add(tag);
		}
		question.setTags(tagList);
		return questionRepo.save(question);
	}
	
	public void deleteAllQuestions() {
		questionRepo.deleteAll();
		return;
	}
	
	public Question addAnswer(Long questionId, Long answerId) {
		Optional<Question> optionalQuestion = questionRepo.findById(questionId);
		Question question = optionalQuestion.get();
		Optional<Answer> optionalAnswer = answerRepo.findById(answerId);
		Answer answer = optionalAnswer.get();
		if (question.getAnswers() == null) {
			List<Answer> answerList = new ArrayList<>();
			answerList.add(answer);
			question.setAnswers(answerList);
		}
		else {
			List<Answer> newAnswerList = question.getAnswers();
			newAnswerList.add(answer);
			question.setAnswers(newAnswerList);
		}
		return questionRepo.save(question);
	}
}
