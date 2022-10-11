package com.scott.accountservice.model;

import com.scott.accountservice.dao.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    @NotNull(message="Must specify 'id' (account owner)")
    @Positive(message="Must be positive long/int")
    private Long id;

    private String accountName;

    @ManyToOne
    @JoinColumn(name="accountType_type", nullable=false)
    @NotNull(message="accountType must not be null")
    private AccountType accountType;

    private double balance = 0;

}
