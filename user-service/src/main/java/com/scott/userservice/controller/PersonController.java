package com.scott.userservice.controller;

import com.scott.userservice.dao.PersonRepository;
import com.scott.userservice.exception.NotFoundException;
import com.scott.userservice.exception.RequestException;
import com.scott.userservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        return new ResponseEntity<>(personRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")), HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> newPerson(@RequestBody Person person) {
        try {
            Person newPerson = personRepository
                    .save(Person.builder()
                            .id(-1l)
                            .name(person.getName())
                            .address(person.getAddress())
                            .age(person.getAge())
                            .job(person.getJob())
                            .email(person.getEmail())
                            .phoneno(person.getPhoneno())
                            .postcode(person.getPostcode())
                            .build());
            return new ResponseEntity<>(newPerson, HttpStatus.OK);
        } catch(Exception e ) {
            throw new RequestException(e.getMessage());
        }


    }
}
