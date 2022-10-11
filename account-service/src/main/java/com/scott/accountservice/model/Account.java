package com.scott.accountservice.model;

import com.scott.accountservice.dao.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long accountNumber;

    @JoinColumn(name = "person_id")
    private Long id;

    private String accountName;

    @ManyToOne
    @JoinColumn(name="accountType_type", nullable=false)
    private AccountType accountType;

    private double balance = 0;

}
