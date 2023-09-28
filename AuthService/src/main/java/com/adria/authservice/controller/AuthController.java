package com.adria.authservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint. No authentication required.";
    }

    @GetMapping("/client")
    @RolesAllowed("client")
    public String clientEndpoint() {
        return "This is a client endpoint. Requires client authentication.";
    }

    @GetMapping("/admin")
    @RolesAllowed("admin")
    public String adminEndpoint() {
        return "This is an admin endpoint. Requires admin authentication.";
    }
}
