package com.scott.userservice.controller;

import com.scott.userservice.dao.PersonRepository;
import com.scott.userservice.exception.NotFoundException;
import com.scott.userservice.exception.RequestException;
import com.scott.userservice.exception.ValidationException;
import com.scott.userservice.model.Person;
import com.scott.userservice.utils.RestUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
@Validated
@AllArgsConstructor
public class PersonController {

    PersonRepository personRepository;


    private final Validator validator;
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") @Min(value =0) long id) {
        return new ResponseEntity<>(personRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")), HttpStatus.OK);
    }

    @GetMapping("/persons/person")
    public ResponseEntity<List<Person>> getAllPeople() {
        try {
            return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/person")
    public ResponseEntity<Person> newPerson(@RequestBody @Valid Person person) {
        if (personRepository.findByEmail(person.getEmail()) != null)
            throw new RequestException("Email Already Exist");

        return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
    }

    @PutMapping("/persons/person")
    public ResponseEntity<Person> updatePersonById(@NotNull @RequestBody Person person) throws RequestException, ValidationException {
        if (person.getId() == null)
            throw new RequestException("User id must be in request body");
        Person existingPerson = personRepository.findById(person.getId()).orElseThrow(() -> new NotFoundException("User not found"));
        BeanUtils.copyProperties(person, existingPerson, RestUtils.nullProperties(person)); // Only replaces values which contain values
        Set<ConstraintViolation<Person>> violations = validator.validate(existingPerson); // validate if new person conforms to constraints

        if (violations.size() > 0){
            throw new ValidationException(violations);
        }

        return new ResponseEntity<>(personRepository.save(existingPerson), HttpStatus.OK);
    }


}
