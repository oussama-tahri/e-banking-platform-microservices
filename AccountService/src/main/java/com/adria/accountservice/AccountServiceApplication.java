package com.adria.accountservice;

import com.adria.accountservice.entities.Account;
import com.adria.accountservice.enums.AccountType;
import com.adria.accountservice.feign.CustomerService;
import com.adria.accountservice.models.Customer;
import com.adria.accountservice.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, CustomerService customerService) {
        return args -> {
            Customer customer=customerService.getCustomerById(1L);
            Customer customer1=customerService.getCustomerById(2L);
            accountRepository.save(new Account(null, UUID.randomUUID().toString(),6000.00,new Date(), AccountType.SAVING,customer.getId()));
            accountRepository.save(new Account(null, UUID.randomUUID().toString(),10000.00,new Date(), AccountType.CURRENT,customer1.getId()));
            accountRepository.save(new Account(null, UUID.randomUUID().toString(),80000,new Date(), AccountType.SAVING,customer.getId()));
            accountRepository.findAll().forEach(account -> {
                System.out.println(account.toString());
            });
        };
    }
}