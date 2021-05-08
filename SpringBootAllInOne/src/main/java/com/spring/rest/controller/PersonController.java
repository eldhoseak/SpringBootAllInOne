package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.rest.AppDetails;
import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.Person;
import com.spring.rest.service.PersonService;


@RestController
public class PersonController {
	@Autowired
	PersonService personService;

	@Value("${test.app.name}")
	String appName;
	
	@Autowired
	AppDetails appDetails;

	@GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAll() {

		// demonstrates the ResponseStatusException
		try {
			return personService.getAllPersons();
		}
		catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person details Not Found", exc);
		}
	}

	@PutMapping(value = "/person")
	public HttpStatus insertPerson(@RequestBody Person person) {
		personService.addPerson(person);
		return HttpStatus.CREATED;
	}


	@GetMapping("/appInfo")
	public String appInfo() throws ResourceNotFoundException {
		return appName;
	}
	
	@GetMapping("/appDetails")
	public AppDetails appDetails() throws ResourceNotFoundException {
		return appDetails;
	}
}

