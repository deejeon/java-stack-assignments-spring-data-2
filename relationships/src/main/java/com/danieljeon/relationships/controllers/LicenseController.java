package com.danieljeon.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.danieljeon.relationships.models.License;
import com.danieljeon.relationships.models.Person;
import com.danieljeon.relationships.services.MainService;

@Controller
public class LicenseController {
	@Autowired
	private final MainService mainService;
	
	public LicenseController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@RequestMapping("/licenses/new")
	public String newLicense(@ModelAttribute("license") License license, Model model) {
		List<Person> persons = mainService.allPersons();
		model.addAttribute("persons", persons);
		return "/licenses/new.jsp";
	}
	
	@RequestMapping(value="/licenses", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("license") License license, BindingResult result) {
		if (result.hasErrors()) {
			return "/licenses/new.jsp";
		}
		else {
			mainService.createLicense(license);
			return "redirect:/persons";
		}
	}
}
