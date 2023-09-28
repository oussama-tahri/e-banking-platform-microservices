package com.adria.transactionservice.exceptions;

public class CardNotActivedException extends Exception{

    public CardNotActivedException(String message) {
        super(message);
    }
}
