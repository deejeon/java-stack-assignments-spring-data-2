package com.danieljeon.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.relationships.models.License;
import com.danieljeon.relationships.models.Person;
import com.danieljeon.relationships.repositories.LicenseRepository;
import com.danieljeon.relationships.repositories.PersonRepository;

@Service
public class MainService {
	@Autowired
	private final PersonRepository personRepository;
	@Autowired
	private final LicenseRepository licenseRepository;
	
	public MainService(PersonRepository personRepository, LicenseRepository licenseRepository) {
		this.personRepository = personRepository;
		this.licenseRepository = licenseRepository;
	}
	
	public List<Person> allPersons() {
		return personRepository.findAll();
	}
	
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}
	
	public Person findPerson(Long id) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		if (optionalPerson.isPresent()) {
			return optionalPerson.get();
		}
		else {
			return null;
		}
	}
	
	public Person updatePerson(Person person) {
		Person editedPerson = this.findPerson(person.getId());
		editedPerson.setFirstName(person.getFirstName());
		editedPerson.setLastName(person.getLastName());
		editedPerson.setLicense(person.getLicense());
		return personRepository.save(person);
	}
	
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
		return;
	}
	
	public List<License> allLicenses() {
		return licenseRepository.findAll();
	}
	
	public License createLicense(License license) {
		License newLicense = licenseRepository.save(license);
		String licenseNumber = "" + (license.getId() / 100000);
		licenseNumber += "" + (license.getId() / 10000);
		licenseNumber += "" + (license.getId() / 1000);
		licenseNumber += "" + (license.getId() / 100);
		licenseNumber += "" + (license.getId() / 10);
		licenseNumber += "" + (license.getId() % 10);
		newLicense.setNumber(licenseNumber);
		licenseRepository.save(newLicense);
		return newLicense;
	}
	
	public License findLicense(Long id) {
		Optional<License> optionalLicense = licenseRepository.findById(id);
		if (optionalLicense.isPresent()) {
			return optionalLicense.get();
		}
		else {
			return null;
		}
	}
	
	public License updateLicense(License license) {
		License editedLicense = this.findLicense(license.getId());
		editedLicense.setNumber(license.getNumber());
		editedLicense.setExpirationDate(license.getExpirationDate());
		editedLicense.setState(license.getState());
		editedLicense.setPerson(license.getPerson());
		return licenseRepository.save(license);
	}
	
	public void deleteLicense(Long id) {
		licenseRepository.deleteById(id);
		return;
	}
}
