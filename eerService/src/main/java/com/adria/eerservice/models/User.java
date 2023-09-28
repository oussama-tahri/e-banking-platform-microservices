package com.adria.eerservice.models;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String  firstname;
    private String  lastname;
}
