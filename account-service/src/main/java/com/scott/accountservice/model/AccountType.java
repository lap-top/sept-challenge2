package com.scott.accountservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer accountNumber;
    private String accountName;

}
