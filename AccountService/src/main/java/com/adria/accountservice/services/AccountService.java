package com.adria.accountservice.services;

import com.adria.accountservice.dtos.AccountDTO;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


public interface AccountService
{

    AccountDTO updateAccount(AccountDTO accountDTO) ;

    void deleteAccount(Long accountId);

    AccountDTO getAccount(Long accountId) throws AccountNotFoundException;



    AccountDTO getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;

    List<AccountDTO> getAccounts();

    AccountDTO saveAccount(AccountDTO accountDTO);
    List<AccountDTO> getAllAccountByCustomerId(Long customerId);



    AccountDTO updateBalance(Long idAccount, double balance) throws AccountNotFoundException;

    double getBalancebyCustomer(Long customerId);
}
