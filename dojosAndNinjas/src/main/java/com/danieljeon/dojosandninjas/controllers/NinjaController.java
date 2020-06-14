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
import com.danieljeon.dojosandninjas.models.Ninja;
import com.danieljeon.dojosandninjas.services.MainService;

@Controller
public class NinjaController {
	@Autowired
	private final MainService mainService;
	
	public NinjaController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@RequestMapping("/ninjas")
	public String index(Model model) {
		List<Ninja> ninjas = mainService.allNinjas();
		model.addAttribute("ninjas", ninjas);
		return "/ninjas/index.jsp";
	}
	
	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = mainService.allDojos();
		model.addAttribute("dojos", dojos);
		return "/ninjas/new.jsp";
	}
	
	@RequestMapping(value = "/ninjas", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if (result.hasErrors()) {
			return "/ninjas/new.jsp";
		}
		else {
			mainService.createNinja(ninja);
			return "redirect:/dojos/"+ninja.getDojo().getId();
		}
	}
	
	@RequestMapping("/ninjas/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Ninja ninja = mainService.findNinja(id);
		model.addAttribute("ninja", ninja);
		return "/ninjas/show.jsp";
	}
	
}
