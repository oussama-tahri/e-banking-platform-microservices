package com.adria.userservice.dtos;

import lombok.Data;

@Data
public class AdminDTO  extends UserDTO{
    private Long id;
    private String username;
    private String password;
    private String email;
    private String  firstname;
    private String  lastname;
    private String phoneNumber;
}
