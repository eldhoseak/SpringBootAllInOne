package com.spring.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.rest.model.Person;
import com.spring.rest.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@MockBean
	PersonService personService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(value = "user")
	public void testAllPersonsEndpoint() throws Exception {
		when(personService.getAllPersons()).thenReturn(Arrays.asList(new Person(1l,34, "Mark","Hall")));
		this.mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"));
	}

	@Test
	@WithMockUser(value = "user")
	public void testAddPersonsEndpoint() throws Exception {
		when(personService.addPerson(any())).thenReturn(true);
		String personJson = "{\"age\": 35,\"firstName\" : \"Yoav\",\"lastName\": \"Karan\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/person")
				.accept(MediaType.APPLICATION_JSON).content(personJson)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
		.andExpect(content().string("\"CREATED\""));
		
		}
}