package com.danieljeon.dojooverflow.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.dojooverflow.models.Question;
import com.danieljeon.dojooverflow.models.Tag;
import com.danieljeon.dojooverflow.repositories.AnswerRepo;
import com.danieljeon.dojooverflow.repositories.QuestionRepo;
import com.danieljeon.dojooverflow.repositories.TagRepo;

@Service
public class TagService {
	@Autowired
	private final QuestionRepo questionRepo;
	
	@Autowired
	private final AnswerRepo answerRepo;
	
	@Autowired
	private final TagRepo tagRepo;
	
	public TagService(QuestionRepo qr, AnswerRepo ar, TagRepo tr) {
		this.questionRepo = qr;
		this.answerRepo = ar;
		this.tagRepo = tr;
	}
	
	public List<Tag> all() {
		return tagRepo.findAll();
	}
	
	public Tag create(String subject) {
		Optional<Tag> optionalTag = tagRepo.findBySubject(subject);
		if (optionalTag.isPresent()) {
			return optionalTag.get();
		}
		else {
			return tagRepo.save(new Tag(subject));
		}
	}
	
	public Tag find(Long id) {
		Optional<Tag> optionalTag = tagRepo.findById(id);
		if (optionalTag.isPresent()) return optionalTag.get();
		else return null;
	}
	
	public void deleteAllTags() {
		tagRepo.deleteAll();
		return;
	}
	
//	public List<Tag> parseTags(Question question, String tagsString) {
//		tagsString = tagsString.toLowerCase();
//		List<Tag> tagList = question.getTags();
//		String subject = "";
//		int i = 0;
//		while (i < tagsString.length() && tagList.size() < 4) {
//			if (tagsString.charAt(i) == ',') {
//				subject = tagsString.substring(0, i);
//				if (!tagRepo.findBySubject(subject).equals(null)) {
//					Tag optionalTag = tagRepo.findBySubject(subject);
//					tagList.add(optionalTag);
//				}
//				else {
//					Tag newTag = new Tag();
//					newTag.setSubject(subject);
//					tagList.add(newTag);
//				}
//				subject = "";
//				tagsString = tagsString.substring(i + 1);
//				i = 0;
//				continue;
//			}
//			if (i == tagsString.length() - 1) {
//				subject = tagsString;
//				Tag newTag = new Tag();
//				newTag.setSubject(subject);
//				tagList.add(newTag);
//				break;
//			}
//			i++;
//		}
//		return tagList;
//	}
}
