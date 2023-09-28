package com.adria.accountservice.services;

import com.adria.accountservice.dtos.AccountDTO;
import com.adria.accountservice.entities.Account;
import com.adria.accountservice.feign.CustomerService;
import com.adria.accountservice.mappers.AccountMapper;
import com.adria.accountservice.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    CustomerService customerService;
    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO)  {


        Account account = accountMapper.fromAccountDto(accountDTO);
        Account saveAccount = accountRepository.save(account);
        return accountMapper.fromAccount(saveAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);

    }

    @Override
    public AccountDTO getAccount(Long accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return accountMapper.fromAccount(account);
    }
    @Override
    public AccountDTO getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if(account==null){
            throw new AccountNotFoundException("account not found");
        }
        System.out.println(account);

        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountDTO> getAccounts() {

        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map(account ->
             accountMapper.fromAccount(account)).collect(Collectors.toList());

    }

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {


            Account account = accountMapper.fromAccountDto(accountDTO);
            account.setAccountNumber(UUID.randomUUID().toString());
            account.setDateCreation(new Date());

            Account saveAccount = accountRepository.save(account);


        return accountMapper.fromAccount(saveAccount);

    }

    @Override
    public List<AccountDTO> getAllAccountByCustomerId(Long customerId) {
        List<Account> accounts=accountRepository.findByCustomerId(customerId);
         List<AccountDTO> accountsDTO=accounts.stream().map(account ->
                accountMapper.fromAccount(account)).collect(Collectors.toList());

        return accountsDTO;
    }



    @Override
    public AccountDTO updateBalance(Long idAccount, double balance) throws AccountNotFoundException {
        AccountDTO accountDTO=getAccount(idAccount);


       return accountMapper.fromAccount(accountRepository.save(accountMapper.fromAccountDto(accountDTO)));


    }
    @Override
    public double getBalancebyCustomer(Long customerId){
        List<AccountDTO> accountsDTO=getAllAccountByCustomerId(customerId);
        AccountDTO totalBalance = accountsDTO.stream()
                .reduce(new AccountDTO(), (accumulator, account) -> {
                    accumulator.setBalance(accumulator.getBalance() + account.getBalance());
                    return accumulator;
                });
        return totalBalance.getBalance();

    }
}
