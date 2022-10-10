package com.scott.userservice.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
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
