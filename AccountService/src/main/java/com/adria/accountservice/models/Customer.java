package com.adria.accountservice.models;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String  firstname;
    private String  lastname;
}
