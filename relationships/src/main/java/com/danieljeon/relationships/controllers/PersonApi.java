package com.danieljeon.relationships.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.danieljeon.relationships.services.MainService;

@RestController
public class PersonApi {
	private final MainService mainService;
	
	public PersonApi(MainService mainService) {
		this.mainService = mainService;
	}
	
}
