package com.danieljeon.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.danieljeon.relationships.models.Person;
import com.danieljeon.relationships.services.MainService;

@Controller
public class PersonController {
	private final MainService mainService;
	
	public PersonController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@RequestMapping("/persons")
	public String index(Model model) {
		List<Person> persons = mainService.allPersons();
		model.addAttribute("persons", persons);
		return "/persons/index.jsp";
	}
	
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "/persons/new.jsp";
	}
	
	@RequestMapping(value="/persons", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "/persons/new.jsp";
		}
		else {
			mainService.createPerson(person);
			return "redirect:/persons";
		}
	}
	
	@RequestMapping("/persons/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Person person = mainService.findPerson(id);
		model.addAttribute("person", person);
		return "/persons/show.jsp";
	}
}
