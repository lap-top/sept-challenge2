package com.scott.userservice.model;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String address;
    @Getter @Setter private String postcode;
    @Getter @Setter private int age;
    @Getter @Setter private String job;
    @Getter @Setter private String email;
    @Getter @Setter  private String phoneno;
}
