package com.scott.accountservice.dao;

import com.scott.accountservice.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
