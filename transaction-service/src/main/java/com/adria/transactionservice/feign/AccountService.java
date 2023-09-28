package com.adria.transactionservice.feign;

import com.adria.transactionservice.models.Account;
import com.adria.transactionservice.models.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountService {
    @GetMapping("/accounts/{accountId}")
    Account getAccountById(@PathVariable(name="accountId") Long accountId);

    @GetMapping("/accounts/accountNumber/{accountNumber}")
    Account getAccountByNumber(@PathVariable(name="accountNumber") String accountNumber);
    @GetMapping("/accounts/customer/{customerId}")
    Account getAccountByCustomer(@PathVariable(name="customerId") Long customerId);
    @PatchMapping("/accounts/{idAccount}/balance")
    Account updateAccount(@PathVariable(name="idAccount") Long accountId,@RequestParam Double balance );
    @PutMapping("/accounts/{idAccount}")
    Account updateAccount( @RequestBody Account account,@PathVariable(name="idAccount") Long accountId);
}