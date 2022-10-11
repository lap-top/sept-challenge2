package com.scott.accountservice.dao;

import com.scott.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>  {
    
    @Query(value="select case when exists(select * from person where id = :userId) then 'true' else 'false' end from dual", nativeQuery = true)
        Boolean userExist(@Param("userId") Long searchId);

    @Query(value="select name FROM person WHERE person.id = :userId", nativeQuery = true)
    String getAccountName(@Param("userId") Long userId);


    Optional<Account> findAccountByAccountNumber(long accountNumber);
}
