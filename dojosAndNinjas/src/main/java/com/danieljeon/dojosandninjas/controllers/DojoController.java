package com.danieljeon.dojosandninjas.controllers;

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

import com.danieljeon.dojosandninjas.models.Dojo;
import com.danieljeon.dojosandninjas.services.MainService;

@Controller
public class DojoController {
	@Autowired
	private final MainService mainService;
	
	public DojoController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@RequestMapping("/dojos")
	public String index(Model model) {
		List<Dojo> dojos = mainService.allDojos();
		model.addAttribute("dojos", dojos);
		return "/dojos/index.jsp";
	}
	
	@RequestMapping("/dojos/new")
	public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
		return "/dojos/new.jsp";
	}
	
	@RequestMapping(value = "/dojos", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "/dojos/new.jsp";
		}
		else {
			mainService.createDojo(dojo);
			return "redirect:/dojos";
		}
	}
	
	@RequestMapping("/dojos/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Dojo dojo = mainService.findDojo(id);
		model.addAttribute("dojo", dojo);
		return "/dojos/show.jsp";
	}
}
