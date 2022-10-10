package com.scott.userservice.controller;

import com.scott.userservice.dao.PersonRepository;
import com.scott.userservice.exception.NotFoundException;
import com.scott.userservice.exception.RequestException;
import com.scott.userservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") @Min(value =0) long id) {
        return new ResponseEntity<>(personRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")), HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> newPerson(@RequestBody @Valid Person person) {
        if (personRepository.findByEmail(person.getEmail()) != null)
            throw new RequestException("Email Already Exist");

        Person newPerson = personRepository.save(person);
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }
}
