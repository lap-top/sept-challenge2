package com.scott.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountType {
    AccountType(Long id) {
        this.type = id;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long type;
    private String name;




}
