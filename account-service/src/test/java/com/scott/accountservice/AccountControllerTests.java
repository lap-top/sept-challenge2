package com.scott.accountservice;

import com.scott.accountservice.controller.AccountController;
import com.scott.accountservice.dao.AccountRepository;
import com.scott.accountservice.dao.AccountTypeRepository;
import com.scott.accountservice.model.Account;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTests {

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    AccountTypeRepository accountTypeRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void Get_all_accounts_status200() throws Exception {
        int size = 10;
        List<Account> accounts = TestUtils.createFakeAccounts(size);
        Mockito.when(accountRepository.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/accounts/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountName", Matchers.is(accounts.get(0).getAccountName())))
                .andExpect(jsonPath("$", Matchers.hasSize(size)));
    }

    @Test
    public void Get_valid_account_status200() throws Exception {
        Long accountNumber = 5l;
        Account testAccount = TestUtils.createFakeAccount(accountNumber);
        Mockito.when(accountRepository.findAccountByAccountNumber(accountNumber)).thenReturn(Optional.of(testAccount));

        mockMvc.perform(get("/account/"+accountNumber))
                .andExpect(status().isOk());
    }

    @Test
    public void Get_invalid_account_status404() throws Exception {
        Long accountId = 5l;
        Mockito.when(accountRepository.findAccountByAccountNumber(accountId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/account/"+accountId))
                .andExpect(status().isNotFound());
    }




}
