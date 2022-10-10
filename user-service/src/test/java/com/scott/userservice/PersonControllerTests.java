package com.scott.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scott.userservice.controller.PersonController;
import com.scott.userservice.dao.PersonRepository;
import com.scott.userservice.model.Person;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    @MockBean
    PersonRepository personRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void Get_all_people_status200() throws Exception {
        int size = 10;
        List<Person> people = TestUtils.createFakePeople(size);
        Mockito.when(personRepository.findAll()).thenReturn(people);

        mockMvc.perform(get("/persons/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(people.get(0).getName())))
                .andExpect(jsonPath("$", Matchers.hasSize(size)));
    }

    @Test
    public void Get_valid_person_status200() throws Exception {
        Long userId = 5l;
        Person testPerson = TestUtils.createFakePerson(userId);
        Mockito.when(personRepository.findById(userId)).thenReturn(Optional.of(testPerson));

        mockMvc.perform(get("/person/"+userId))
                .andExpect(status().isOk());
    }

    @Test
    public void Get_invalid_person_status404() throws Exception {
        Long userId = 5l;
        Mockito.when(personRepository.findById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/person/"+userId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void Post_negative_person_age_status400() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Long userId = 5l;
        Person testPerson = TestUtils.createFakePerson(userId);
        testPerson.setAge(-1);

        System.out.println(mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPerson))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void Post_valid_person_status200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Long userId = 5l;
        Person testPerson = TestUtils.createFakePerson(userId);

        System.out.println(mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPerson))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void Post_existing_email_status400() throws Exception {
        Long userId = 5l;
        Person testPerson = TestUtils.createFakePerson(userId);
        Mockito.when(personRepository.findByEmail(testPerson.getEmail())).thenReturn(testPerson);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPerson))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }






}
