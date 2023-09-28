package com.adria.accountservice.repositories;

import com.adria.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByCustomerId(Long customerId);
    Account findByAccountNumber(String accountNumber);

}
