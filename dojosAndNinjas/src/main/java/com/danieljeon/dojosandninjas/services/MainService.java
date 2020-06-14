package com.danieljeon.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.dojosandninjas.models.Dojo;
import com.danieljeon.dojosandninjas.models.Ninja;
import com.danieljeon.dojosandninjas.repositories.DojoRepo;
import com.danieljeon.dojosandninjas.repositories.NinjaRepo;

@Service
public class MainService {
	@Autowired
	private final DojoRepo dojoRepo;
	@Autowired
	private final NinjaRepo ninjaRepo;
	
	public MainService(DojoRepo dojoRepo, NinjaRepo ninjaRepo) {
		this.dojoRepo = dojoRepo;
		this.ninjaRepo = ninjaRepo;
	}
	
	public List<Dojo> allDojos() {
		return dojoRepo.findAll();
	}
	
	public Dojo createDojo(Dojo dojo) {
		return dojoRepo.save(dojo);
	}
	
	public Dojo findDojo(Long id) {
		Optional<Dojo> optionalDojo = dojoRepo.findById(id);
		if (optionalDojo.isPresent()) {
			return optionalDojo.get();
		}
		else {
			return null;
		}
	}
	
	public Dojo updateDojo(Dojo dojo) {
		Dojo editedDojo = this.findDojo(dojo.getId());
		editedDojo.setName(dojo.getName());
		return dojoRepo.save(dojo);
	}
	
	public void deleteDojo(Long id) {
		dojoRepo.deleteById(id);
		return;
	}
	
	public List<Ninja> allNinjas() {
		return ninjaRepo.findAll();
	}
	
	public Ninja createNinja(Ninja ninja) {
		return ninjaRepo.save(ninja);
	}
	
	public Ninja findNinja(Long id) {
		Optional<Ninja> optionalNinja = ninjaRepo.findById(id);
		if (optionalNinja.isPresent()) {
			return optionalNinja.get();
		}
		else {
			return null;
		}
	}
	
	public Ninja updateNinja(Ninja ninja) {
		Ninja editedNinja = this.findNinja(ninja.getId());
		editedNinja.setFirstName(ninja.getFirstName());
		editedNinja.setLastName(ninja.getLastName());
		editedNinja.setAge(ninja.getAge());
		return ninjaRepo.save(ninja);
	}
	
	public void deleteNinja(Long id) {
		ninjaRepo.deleteById(id);
		return;
	}
}
