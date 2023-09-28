package com.adria.transactionservice.exceptions;

public class BalanceNotSufficentException extends Exception {
    public BalanceNotSufficentException(String balance_not_sufficient) {
        super(balance_not_sufficient);
    }
}
