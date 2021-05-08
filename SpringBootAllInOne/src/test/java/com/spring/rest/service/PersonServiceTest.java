package com.spring.rest.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.rest.model.Person;
import com.spring.rest.repository.PersonRepository;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void testFindByName(){

        when(personRepository.findAll()).thenReturn(Arrays.asList(new Person(1l,34, "Mark","Hall")));

        List<Person> persons =  personService.getAllPersons();
        assertEquals("Mark", persons.get(0).getFirstName());
    }
}