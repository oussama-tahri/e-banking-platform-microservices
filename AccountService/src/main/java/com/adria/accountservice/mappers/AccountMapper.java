package com.adria.accountservice.mappers;

import com.adria.accountservice.dtos.AccountDTO;
import com.adria.accountservice.entities.Account;
import com.adria.accountservice.feign.CustomerService;
import com.adria.accountservice.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountMapper {
CustomerService customerService;
    public AccountDTO fromAccount(Account account){
        AccountDTO accountDTO=new AccountDTO();
        Customer customer=customerService.getCustomerById(account.getCustomerId());
        if(customer!=null) {
            BeanUtils.copyProperties(account, accountDTO);
            accountDTO.setCustomer(customer);
        }
        return  accountDTO;
    }

    public Account fromAccountDto(AccountDTO accountDTO){
        Account account=new Account();
        BeanUtils.copyProperties(accountDTO,account);
        return  account;
    }



}
