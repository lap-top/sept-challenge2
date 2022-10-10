package com.scott.userservice;

import com.github.javafaker.Faker;
import com.scott.userservice.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestUtils {
    static private Faker faker = new Faker(new Locale("en-AU"));
    static public Person createFakePerson() {
        return new Person((long) faker.number().numberBetween(0, 10000), faker.address().firstName(), faker.address().fullAddress(), faker.number().digits(4), 10, faker.job().title(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone());
    }

    static public Person createFakePerson(Long id) {
        return new Person(id, faker.name().fullName(), faker.address().fullAddress(), faker.address().zipCode(), 10, faker.job().title(), faker.internet().emailAddress(), faker.number().digits(10));
    }

    static public List<Person> createFakePeople(int n) {
        List<Person> people = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            people.add(createFakePerson(i+1l));
        }
        return people;
    }
}


/*

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank  private String address;
    @Size(min=4, max=4, message= "Postcodes must be 4 digits")
    private String postcode;
    @NotNull @Min(value =0, message="Age must be at least 0") @Max(value=200, message="Age is too high")
    int age;
    @NotBlank private
    String job;
    @NotBlank @Email(message = "Email should be valid format")
    private String email;
    @NotBlank @Size (min = 10, max = 10, message = "Phone numbers must have length of 10")
    private String phoneno;
}
 */