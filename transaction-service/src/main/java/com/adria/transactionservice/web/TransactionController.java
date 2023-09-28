package com.adria.transactionservice.web;


import com.adria.transactionservice.dtos.TransactionDTO;

import com.adria.transactionservice.exceptions.BalanceNotSufficentException;
import com.adria.transactionservice.exceptions.CardNotActivedException;
import com.adria.transactionservice.exceptions.CardNotFoundException;

import com.adria.transactionservice.exceptions.TransactionNotFoundException;
import com.adria.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionController {
    TransactionService transactionService;
    @GetMapping
    //@PreAuthorize("hasAuthority('USER')")
    public List<TransactionDTO> getTransactions(){
        return transactionService.getTransactions();
    }
    @GetMapping("/{transactionId}")
    //@PreAuthorize("hasAuthority('USER')")
    public TransactionDTO getTransaction(@PathVariable Long transactionId) throws TransactionNotFoundException, AccountNotFoundException {
        return transactionService.getTransaction(transactionId);
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public TransactionDTO addTransaction(@RequestBody TransactionDTO transactionDTO) throws BalanceNotSufficentException, CardNotActivedException, CardNotFoundException {
        return transactionService.saveTransaction(transactionDTO);
    }

    @GetMapping("/card/{cardId}")
   // @PreAuthorize("hasAuthority('USER')")
    public List<TransactionDTO> getTransactions(@PathVariable Long cardId){
        return transactionService.getTransactionsByCard(cardId);
    }

    @GetMapping("/customer/{customerId}")
    //@PreAuthorize("hasAuthority('USER')")
    public List<TransactionDTO> getTransactionsbyCustomer(@PathVariable Long customerId){
        return transactionService.getTransactionsByCard(customerId);
    }
    @GetMapping("/recent/customer/{customerId}")
    //@PreAuthorize("hasAuthority('USER')")
    public List<TransactionDTO> getRecentTransactionsbyCustomer(@PathVariable Long customerId){
        return transactionService.getRecentTransactions(customerId);
    }
    @GetMapping("/account/{accountId}")
   // @PreAuthorize("hasAuthority('USER')")
    public List<TransactionDTO> getTransactionsbyAccount(@PathVariable Long accountId){
        return transactionService.getTransactionsByCard(accountId);
    }

}
