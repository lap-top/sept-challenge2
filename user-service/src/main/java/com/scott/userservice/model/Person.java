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
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message="address must not be blank")  private String address;
    @Size(min=4, max=4, message= "postcode must be 4 digits")
    private String postcode;
    @NotNull(message = "age must not be null") @Min(value =0, message="age must be at least 0") @Max(value=200, message="age is too high")
    int age;
    @NotBlank(message="job must not be blank") private
    String job;
    @NotBlank(message="email must not be blank") @Email(message = "email should be valid format")
    private String email;
    @NotBlank(message="phoneno must not be blank") @Size (min = 10, max = 10, message = "Phone numbers must have length of 10")
    private String phoneno;
}
