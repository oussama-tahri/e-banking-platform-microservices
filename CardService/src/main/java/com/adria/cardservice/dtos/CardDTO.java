package com.adria.cardservice.dtos;

import com.adria.cardservice.enums.CardType;
import com.adria.cardservice.models.Account;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class CardDTO {

    private Long id;
    private String cardNumber;
    private LocalDate expirationDate;
    private boolean active;
    private CardType cardType;
    private Long accountId; // To store the account ID
    private Account account; // To store the associated account information
}
