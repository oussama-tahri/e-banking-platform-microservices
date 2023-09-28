package com.adria.accountservice.web;

import com.adria.accountservice.dtos.AccountDTO;
import com.adria.accountservice.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


@RestController
@RequestMapping(path = "/accounts")
@AllArgsConstructor
@Slf4j
public class AccountController {

    AccountService accountService;
    @GetMapping("/{accountId}")
    //@PreAuthorize("hasAuthority('USER')")
    public AccountDTO getAccount(@PathVariable(name ="accountId") Long accountId) throws AccountNotFoundException {
        return accountService.getAccount(accountId);

    }
    @GetMapping
    //@PreAuthorize("hasAuthority('USER')")
    public List<AccountDTO> getAccounts(){
        return accountService.getAccounts();
    }
    @GetMapping("/customer/{customerId}")
    //@PreAuthorize("hasAuthority('USER')")
    public List<AccountDTO> getAccountsByCustomerId(@PathVariable(name = "customerId") Long customerId){
        System.out.println("ted"+accountService.getAllAccountByCustomerId(customerId));
        return accountService.getAllAccountByCustomerId(customerId);
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO){
        return accountService.saveAccount(accountDTO);
    }
    @PutMapping("/{idAccount}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public AccountDTO updateAccount(@RequestBody AccountDTO accountDTO,@PathVariable Long idAccount)  {
        accountDTO.setId(idAccount);
        return accountService.updateAccount(accountDTO);
    }
    @DeleteMapping("/{idAccount}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAccount(@PathVariable Long idAccount){
        accountService.deleteAccount(idAccount);
    }


    @PatchMapping("/{idAccount}/balance")
   // @PreAuthorize("hasAuthority('ADMIN')")
    public AccountDTO updateBalance(@PathVariable  Long idAccount,@RequestParam Double balance ) throws AccountNotFoundException{
        return accountService.updateBalance(idAccount,balance);
    }
    @GetMapping("/accountNumber/{accountNumber}")
    //@PreAuthorize("hasAuthority('USER')")
    public AccountDTO getAccountByAccountNumber(@PathVariable String accountNumber) throws AccountNotFoundException {
        return accountService.getAccountByAccountNumber(accountNumber);
    }
    @GetMapping("/balance/customer/{customerId}")
   // @PreAuthorize("hasAuthority('USER')")
    public double getBalnceByCustomerId(@PathVariable(name = "customerId") Long customerId){

        return accountService.getBalancebyCustomer(customerId);
    }



}
