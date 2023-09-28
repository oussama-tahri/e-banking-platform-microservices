package com.adria.accountservice.dtos;

import com.adria.accountservice.enums.AccountType;
import com.adria.accountservice.models.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private double balance;
    private Date dateCreation;
    private Long  customerId;
    private AccountType type;
    private Customer customer;
}
