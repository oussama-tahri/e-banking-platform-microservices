package com.adria.accountservice.feign;

import com.adria.accountservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface CustomerService {
    @GetMapping(path = "/users/{id}")
    public Customer getCustomerById(@PathVariable(name="id") Long customerId);

}
