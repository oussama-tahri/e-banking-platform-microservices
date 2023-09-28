package com.adria.transactionservice.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Card {
    private Long id;
    private String cardNumber;
    private LocalDate expirationDate;
    private boolean active;
    private String cardType;
    private Long accountId;

}
