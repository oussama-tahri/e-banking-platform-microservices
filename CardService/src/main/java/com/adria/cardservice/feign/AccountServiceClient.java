package com.adria.cardservice.feign;

import com.adria.cardservice.models.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Name of the microservice for accounts
@FeignClient(name = "account-service")
public interface AccountServiceClient {
    @GetMapping("/accounts/{accountId}")
    Account getAccountById(@PathVariable(name="accountId") Long accountId);
}