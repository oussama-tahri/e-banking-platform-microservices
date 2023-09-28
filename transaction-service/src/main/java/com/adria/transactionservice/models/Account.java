package com.adria.transactionservice.models;


import lombok.Data;

@Data
public class Account {
    private Long id;
    private String accountNumber;
    private double balance;
    private Long  customerId;
    private String type;



}