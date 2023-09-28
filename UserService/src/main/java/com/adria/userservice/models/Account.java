package com.adria.userservice.models;



import com.adria.userservice.enums.AccountStatus;
import com.adria.userservice.enums.AccountType;
import lombok.Data;

@Data
public class Account {
    private Long id;
    private String accountNumber;
    private double balance;
    private AccountType type;
    private AccountStatus status;
}