package com.spring.rest.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.rest.model.Person;

public interface PersonRepository<P> extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
}