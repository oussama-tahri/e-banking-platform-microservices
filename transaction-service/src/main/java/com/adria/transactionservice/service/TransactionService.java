package com.adria.transactionservice.service;

import com.adria.transactionservice.dtos.TransactionDTO;
import com.adria.transactionservice.exceptions.BalanceNotSufficentException;
import com.adria.transactionservice.exceptions.CardNotActivedException;
import com.adria.transactionservice.exceptions.CardNotFoundException;
import com.adria.transactionservice.exceptions.TransactionNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface TransactionService
{
TransactionDTO saveTransaction(TransactionDTO transactionDTO) throws BalanceNotSufficentException, CardNotFoundException, CardNotActivedException;
TransactionDTO getTransaction(Long transactionId) throws AccountNotFoundException, TransactionNotFoundException;
List<TransactionDTO> getTransactions();
List<TransactionDTO> getTransactionsByCard(Long cardId);


    List<TransactionDTO> getTransactionsByCustomer(Long customerId);

    List<TransactionDTO> getRecentTransactions(Long customerId);

    List<TransactionDTO> getTransactionsByAccount(Long accountId);
}
