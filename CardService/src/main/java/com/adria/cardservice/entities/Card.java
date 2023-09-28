package com.adria.cardservice.entities;

import com.adria.cardservice.enums.CardType;
import com.adria.cardservice.models.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private LocalDate expirationDate;
    private boolean active;
    @Transient
    private Account account;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private Long accountId; // To store the account ID
}
