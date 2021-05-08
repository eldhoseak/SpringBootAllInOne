package com.spring.rest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.rest.model.Person;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {


    @Autowired
    private PersonRepository<Person> personRepository;

    @Test
    public void testfindByFirstName(){

    personRepository.save(new Person(1l,34, "Mark","Hall"));
    Person person = (Person) personRepository.findByFirstName("Mark").get(0);

    assertEquals("Mark", person.getFirstName());
    
    personRepository.deleteAll();
    Assertions.assertThat(personRepository.findAll()).isEmpty();

}


}