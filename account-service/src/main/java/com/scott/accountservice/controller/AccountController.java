package com.scott.accountservice.controller;

import com.scott.accountservice.dao.AccountRepository;
import com.scott.accountservice.dao.AccountTypeRepository;
import com.scott.accountservice.exception.NotFoundException;
import com.scott.accountservice.exception.RequestException;
import com.scott.accountservice.model.Account;
import com.scott.accountservice.utils.RestUtils;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@Validated
public class AccountController {
    private final AccountRepository accountRepository;
    private final AccountTypeRepository typeRepository;

    @PostMapping("/account")
    public ResponseEntity<Account> newAccount(@RequestBody @Valid Account account) {
        if (!accountRepository.userExist(account.getId()))
            throw new NotFoundException("Person with specified id does not exist");

        account.setAccountType(typeRepository.findById(account.getAccountType().getType()).orElseThrow(() -> new NotFoundException("Invalid accountType, out of range"))); // Throw exception for invalid account id
        if (account.getAccountName() == null)
            account.setAccountName(accountRepository.getAccountName(account.getId()));

        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@Min(value=1, message="accountNumber must be a positive long/int") @PathVariable("accountNumber")  long accountNumber) {
        return new ResponseEntity<>(accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(() -> new NotFoundException("User not found")), HttpStatus.OK);
    }

    @GetMapping("/accounts/account")
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accounts/account")
    public ResponseEntity<Account> updateAccountById( @RequestBody Account account) throws Exception  {
        if (account.getAccountNumber() == null)
            throw new RequestException("accountNumber must be in request body");
        Account existingAccount =  accountRepository.findAccountByAccountNumber(account.getAccountNumber()).orElseThrow(() -> new NotFoundException("User not found"));

        BeanUtils.copyProperties(account, existingAccount, RestUtils.nullProperties(account)); // Only replaces values which contain values

        return new ResponseEntity<>(accountRepository.save(existingAccount), HttpStatus.OK);
    }

    @DeleteMapping("/accounts/account")
    public ResponseEntity<Account> deleteAccount( @RequestBody Account account) {
        account = accountRepository.findAccountByAccountNumber(account.getAccountNumber()).orElseThrow(() -> new NotFoundException("Invalid account for accountNumber"));
        accountRepository.delete(account);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }
}
