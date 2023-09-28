package com.adria.userservice.dtos;


import com.adria.userservice.models.Account;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO  extends UserDTO{
    private Long id;
    private String username;
    private String password;
    private String email;
    private String  firstname;
    private String  lastname;
    private List<Account> bankAccounts;




}
