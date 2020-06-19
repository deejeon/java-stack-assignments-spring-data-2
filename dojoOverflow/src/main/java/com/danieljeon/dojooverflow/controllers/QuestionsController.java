package com.danieljeon.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.danieljeon.dojooverflow.models.Answer;
import com.danieljeon.dojooverflow.models.Question;
import com.danieljeon.dojooverflow.services.AnswerService;
import com.danieljeon.dojooverflow.services.QuestionService;
import com.danieljeon.dojooverflow.services.TagService;

@Controller
public class QuestionsController {
	@Autowired
	private final QuestionService questionService;
	@Autowired
	private final AnswerService answerService;
	@Autowired
	private final TagService tagService;
	
	public QuestionsController(QuestionService qs, AnswerService as, TagService ts) {
		this.questionService = qs;
		this.answerService = as;
		this.tagService = ts;
	}
	
	@RequestMapping("/questions")
	public String dashboard(Model model) {
		List<Question> questions = questionService.all();
		model.addAttribute("questions", questions);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("q") Question q) {
		return "new.jsp";
	}
	
	@RequestMapping(value = "/questions", method = RequestMethod.POST)
	public String createQuestion(@Valid @ModelAttribute("q") Question q, BindingResult result, @RequestParam("tagsString") String tagsString, Model model) {
		if (result.hasErrors()) {
			return "new.jsp";
		}
		List<String> tagList = Arrays.asList(tagsString.split("\\s*,\\s*"));
		if (tagList.size() > 3) {
			model.addAttribute("tagError", "Cannot have more than 3 tags");
			return "new.jsp";
		}
		Question createdQuestion = questionService.create(q);
		List<Long> createdTags = new ArrayList<>();
		for (String subject : tagList) {
			createdTags.add(tagService.create(subject).getId());
		}
		questionService.setTagList(createdQuestion.getId(), createdTags);
		
		return "redirect:/questions";
	}
	
	@RequestMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model, @ModelAttribute("newAns") Answer newAns) {
		Question question = questionService.find(id);
		if (question.getAnswers() == null) {
			List<Answer> answerList = new ArrayList<>();
			model.addAttribute("answers", answerList);
		}
		else {
			List<Answer> answerList = question.getAnswers();
			model.addAttribute("answers", answerList);
		}
		model.addAttribute("question", question);
		return "question.jsp";
	}
	
	@RequestMapping(value="/answers", method = RequestMethod.POST)
	public String createAnswer(@Valid @ModelAttribute("newAns") Answer newAns, BindingResult result, @RequestParam("questionId") Long questionId, Model model) {
		if (result.hasErrors()) {
			Question question = questionService.find(questionId);
			if (question.getAnswers() == null) {
				List<Answer> answerList = new ArrayList<>();
				model.addAttribute("answers", answerList);
			}
			else {
				List<Answer> answerList = question.getAnswers();
				model.addAttribute("answers", answerList);
			}
			model.addAttribute("question", question);
			return "question.jsp";
		}
		Answer newAnswer = answerService.create(newAns);
		answerService.addAnswerToQuestion(newAnswer.getId(), questionId);
		return "redirect:/questions/" + questionId;
	}
	
	@RequestMapping("/clearDB")
	public String clearDB() {
		tagService.deleteAllTags();
		questionService.deleteAllQuestions();
		return "redirect:/questions";
	}
}
