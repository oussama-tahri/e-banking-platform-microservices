package com.adria.transactionservice.dtos;

import com.adria.transactionservice.models.Account;
import com.adria.transactionservice.models.Card;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {

    private Long id;
    private double montant;
    private Date dateTransaction;
    private String description;
    private  Long cardId;
    private Card card;
    private Account account;
    private String destination;



}
