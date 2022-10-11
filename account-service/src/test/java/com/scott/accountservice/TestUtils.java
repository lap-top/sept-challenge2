package com.scott.accountservice;

import com.github.javafaker.Faker;
import com.scott.accountservice.model.Account;
import com.scott.accountservice.model.AccountType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestUtils {
    static private Faker faker = new Faker(new Locale("en-AU"));

    static public Account createFakeAccount(Long id) {
        return new Account(id, Integer.toUnsignedLong(faker.number().numberBetween(1, 10000)), faker.name().fullName(), new AccountType(1l, "Savings"), 10d);
    }

    static public List<Account> createFakeAccounts(int n) {
        List<Account> people = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            people.add(createFakeAccount(i+1l));
        }
        return people;
    }
}